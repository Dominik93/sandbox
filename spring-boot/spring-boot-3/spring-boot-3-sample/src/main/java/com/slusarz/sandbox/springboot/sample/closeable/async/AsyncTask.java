package com.slusarz.sandbox.springboot.sample.closeable.async;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * Simulate async task such as thread listening on incoming messages
 * */
@Slf4j
public class AsyncTask {

    @Async
    public void task() throws InterruptedException {
        while (true) {
            log.info("Execute task");
            Thread.sleep(1000);
        }
    }

}
