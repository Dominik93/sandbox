package com.slusarz.sandbox.springboot.starter.api;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfiguration
@Conditional(ModuleCondition.class)
public @interface ModuleAutoConfiguration {

    String name() default "";

}
