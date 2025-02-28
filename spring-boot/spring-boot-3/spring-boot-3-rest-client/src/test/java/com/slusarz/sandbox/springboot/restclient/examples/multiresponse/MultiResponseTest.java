package com.slusarz.sandbox.springboot.restclient.examples.multiresponse;

import com.slusarz.sandbox.springboot.restclient.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@EnableWireMock({@ConfigureWireMock(name = "my-mock", port = 8000)})
class MultiResponseTest {

    @Autowired
    private MultiResponse multiResponse;

    @Test
    void shouldCovertResponseToCorrectObject() {
        assertThat(multiResponse.multiResponse("200")).isExactlyInstanceOf(SuccessResponse.class);
        assertThat(multiResponse.multiResponse("422")).isExactlyInstanceOf(Error422Response.class);
        assertThat(multiResponse.multiResponse("500")).isExactlyInstanceOf(Error500Response.class);
    }
}