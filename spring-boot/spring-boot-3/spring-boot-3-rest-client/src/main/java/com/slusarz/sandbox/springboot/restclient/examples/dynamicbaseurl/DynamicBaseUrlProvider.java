package com.slusarz.sandbox.springboot.restclient.examples.dynamicbaseurl;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Builder
public class DynamicBaseUrlProvider {

    @Builder.Default
    private Map<String, String> moduleConverters = new HashMap<>();

    @Builder.Default
    private Function<String, String> defaultConverter = module -> String.format("http://localhost:8000/%s", module);

    public String provide(final String module) {
        return moduleConverters.getOrDefault(module, defaultConverter.apply(module));
    }

}
