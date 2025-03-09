package com.slusarz.sandbox.springboot.testcontainers.postgre;

import com.slusarz.sandbox.springboot.testcontainers.SampleRepositoryFirstTest;
import com.slusarz.sandbox.springboot.testcontainers.initializer.PostgreInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = PostgreInitializer.class)
class PostgreSampleRepositoryFirstTest extends SampleRepositoryFirstTest {}