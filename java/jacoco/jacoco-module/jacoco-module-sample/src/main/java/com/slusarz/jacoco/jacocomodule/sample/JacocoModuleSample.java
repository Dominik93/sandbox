package com.slusarz.jacoco.jacocomodule.sample;

import java.util.Objects;

public class JacocoModuleSample {

    public String component(String value) {
        if (Objects.isNull(value)) {
            return "";
        }
        return value.repeat(5);
    }

}
