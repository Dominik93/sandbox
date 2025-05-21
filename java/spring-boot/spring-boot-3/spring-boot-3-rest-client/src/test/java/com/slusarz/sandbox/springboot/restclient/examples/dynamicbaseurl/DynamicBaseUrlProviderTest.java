package com.slusarz.sandbox.springboot.restclient.examples.dynamicbaseurl;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicBaseUrlProviderTest {

    @Test
    void shouldProvideBaseUrlFromDefaultConverter() {
        String baseUrl = DynamicBaseUrlProvider.builder().build().provide("sample");

        assertThat(baseUrl).isEqualTo("http://localhost:8000/sample");
    }

    @Test
    void shouldProvideBaseUrlFromCustomConverter() {
        String baseUrl = DynamicBaseUrlProvider.builder()
                .moduleConverters(Map.of("sample", "http://localhost:8000/custom"))
                .build().provide("sample");

        assertThat(baseUrl).isEqualTo("http://localhost:8000/custom");
    }

}