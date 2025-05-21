package com.slusarz.jacoco.jacocomodule.example;

import java.util.Objects;

public class JacocoModuleExample {

    public String component(String value) {
        if (Objects.isNull(value)) {
            return "";
        }
        return value.repeat(5);
    }

}
