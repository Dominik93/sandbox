package com.slusarz.jacoco.jacococomponent;

import java.util.Objects;

public class JacocoComponent {

    public String component(String value) {
        if (Objects.isNull(value)) {
            return "";
        }
        return value.repeat(5);
    }

}
