package com.slusarz.sandbox.springboot.testcontainers.initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public class MySqlInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static MySQLContainer<?> database = new MySQLContainer<>(DockerImageName.parse("mysql:5.7.34"));

    static {
        Startables.deepStart(database).join();
    }

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        TestPropertyValues.of(
                "spring.datasource.url=" + database.getJdbcUrl(),
                "spring.datasource.username=" + database.getUsername(),
                "spring.datasource.password=" + database.getPassword()
        ).applyTo(ctx.getEnvironment());
    }

}
