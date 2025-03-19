package com.slusarz.sandbox.springboot.restclient.examples.timeout;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class TimeoutTest {

    @Autowired
    private Timeout timeout;

    @Test
    void shouldThrowTimeoutException() {
        assertThatThrownBy(() -> timeout.timeout("5")).isInstanceOf(ResourceAccessException.class);
    }

    @Test
    void shouldNotThrowTimeoutException() {
        assertThatNoException().isThrownBy(() -> timeout.timeout("1"));
    }

}