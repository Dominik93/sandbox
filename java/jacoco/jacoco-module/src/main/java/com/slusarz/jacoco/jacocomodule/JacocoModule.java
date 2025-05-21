package com.slusarz.jacoco.jacocomodule;

import java.util.Objects;

public class JacocoModule {

    public String component(String value) {
        if (Objects.isNull(value)) {
            return "";
        }
        return value.repeat(5);
    }

}
