package com.slusarz.sandbox.springboot.sample.closeable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.function.Function;

@Slf4j
@AllArgsConstructor
public class CloseableSpringApplicationBuilder extends SpringApplicationBuilder {
    public static Function<SpringApplicationBuilder, Boolean> NONE_WEB_TYPE =
            builder -> WebApplicationType.NONE.equals(builder.application().getWebApplicationType());

    private final Function<SpringApplicationBuilder, Boolean> closable;

    public static CloseableSpringApplicationBuilder closeOn(Function<SpringApplicationBuilder, Boolean> closable) {
        return new CloseableSpringApplicationBuilder(closable);
    }

    @Override
    public ConfigurableApplicationContext run(String... args) {
        ConfigurableApplicationContext run = super.run(args);
        if (closable.apply(this)) {
            log.info("Close application");
            run.close();
        }
        return run;
    }
}
