package com.slusarz.sandbox.springboot.restclient.examples.header;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class HeadersTest {

    @Autowired
    private Headers headers;

    @Test
    void shouldAddDefaultHeader() {
        assertThatNoException().isThrownBy(() -> headers.defaultHeader());
    }

    @Test
    void shouldAddCustomHeader() {
        assertThatNoException().isThrownBy(() -> headers.customHeader("X-CUSTOM-SAMPLE-VALUE"));
    }


}