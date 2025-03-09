package com.slusarz.sandbox.springboot.testcontainers.mysql;

import com.slusarz.sandbox.springboot.testcontainers.SampleRepositorySecondTest;
import com.slusarz.sandbox.springboot.testcontainers.initializer.MySqlInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = MySqlInitializer.class)
public class MySqlSampleRepositorySecondTest extends SampleRepositorySecondTest {}