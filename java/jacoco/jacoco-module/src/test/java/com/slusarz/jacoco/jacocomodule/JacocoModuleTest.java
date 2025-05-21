package com.slusarz.jacoco.jacocomodule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class JacocoModuleTest {

    @Test
    void shouldReturnValueMultipliedByFive() {
        JacocoModule jacocoComponent = new JacocoModule();

        String component = jacocoComponent.component("1");

        assertThat(component).isEqualTo("11111");
    }

}