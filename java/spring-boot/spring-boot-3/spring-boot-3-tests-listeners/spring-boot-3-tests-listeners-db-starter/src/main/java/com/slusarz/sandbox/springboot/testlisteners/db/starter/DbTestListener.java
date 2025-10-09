package com.slusarz.sandbox.springboot.testlisteners.db.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

@Slf4j
public class DbTestListener implements TestExecutionListener, Ordered {

    public void beforeTestClass(TestContext testContext) throws Exception {
        log.info("beforeTestClass : {}", testContext.getTestClass());
    }

    public void prepareTestInstance(TestContext testContext) throws Exception {
        log.info("prepareTestInstance : {}", testContext.getTestClass());
    }

    public void beforeTestMethod(TestContext testContext) throws Exception {
        log.info("beforeTestMethod : {}", testContext.getTestMethod());
    }

    public void afterTestMethod(TestContext testContext) throws Exception {
        log.info("afterTestMethod : {}", testContext.getTestMethod());
    }

    public void afterTestClass(TestContext testContext) throws Exception {
        log.info("afterTestClass : {}", testContext.getTestClass());
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
