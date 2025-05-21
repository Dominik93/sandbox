package com.slusarz.sandbox.springboot.restclient.examples.method;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class MethodTest {

    @Autowired
    private Method method;

    @Test
    void shouldMakeGetWithPathParamRequest() {
        List<String> sample = method.get("1");

        assertThat(sample).isEqualTo(List.of("1", "2"));
    }

    @Test
    void shouldMakeGetRequest() {
        List<String> sample = method.get();

        assertThat(sample).isEqualTo(List.of("1", "2"));
    }

    @Test
    void shouldMakeDeleteRequest() {
        assertThatNoException().isThrownBy(() -> method.delete("1"));
    }

    @Test
    void shouldMakePutRequest() {
        assertThatNoException().isThrownBy(() -> method.put("1"));
    }

}