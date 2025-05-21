package com.slusarz.jacoco.jacocomodule.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class JacocoModuleExampleTest {

    @Test
    void shouldReturnValueMultipliedByFive() {
        JacocoModuleExample jacocoComponent = new JacocoModuleExample();

        String component = jacocoComponent.component("1");

        assertThat(component).isEqualTo("11111");
    }

}