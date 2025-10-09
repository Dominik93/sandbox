package com.slusarz.sandbox.springboot.testlisteners.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
public class SampleTest {
    
    @Autowired
    SampleService sampleService;

    @Test
    void shouldTest() {
        assertThat(sampleService.get()).isEqualTo("OK");
    }
}
