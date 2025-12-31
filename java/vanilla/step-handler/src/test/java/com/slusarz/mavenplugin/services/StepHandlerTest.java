package com.slusarz.mavenplugin.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StepHandlerTest {

    @Test
    void shouldHandleSteps() {
        StepHandler handler = new StepHandler();
        Handler<String, Integer> stepOne = String::length;
        Handler<Integer, Integer> stepTwo = params -> params * 5;
        Handler<Integer, String> stepThree = "."::repeat;

        handler.add(stepOne);
        handler.add(stepTwo);
        handler.add(stepThree);

        Assertions.assertEquals(handler.execute("2"), ".....");
    }


}