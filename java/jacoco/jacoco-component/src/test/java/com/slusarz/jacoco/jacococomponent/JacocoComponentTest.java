package com.slusarz.jacoco.jacococomponent;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JacocoComponentTest {

    @Test
    void shouldReturnEmptyStringWhenNull() {
        JacocoComponent jacocoComponent = new JacocoComponent();

        String component = jacocoComponent.component(null);

        assertThat(component).isEmpty();
    }

}