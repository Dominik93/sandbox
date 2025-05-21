package com.slusarz.jacoco.jacocomodule.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class JacocoModuleSampleTest {

    @Test
    void shouldReturnValueMultipliedByFive() {
        JacocoModuleSample jacocoComponent = new JacocoModuleSample();

        String component = jacocoComponent.component("1");

        assertThat(component).isEqualTo("11111");
    }

}