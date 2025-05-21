package com.slusarz.sandbox.springboot.starter.api;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

public class ModuleCondition implements Condition {
    private static final String MODULES_ENABLED = "company.modules.enabled";
    private static final String MODULE_ENABLED = "company.modules.%s.enabled";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ModuleAutoConfiguration.class.getName());
        String name = (String) Objects.requireNonNull(attributes).get("name");
        Environment environment = context.getEnvironment();
        Boolean modulesEnabled = environment.getProperty(MODULES_ENABLED, Boolean.class, true);
        Boolean moduleEnabled = environment.getProperty(String.format(MODULE_ENABLED, name), Boolean.class, true);
        return modulesEnabled && moduleEnabled;
    }
}
