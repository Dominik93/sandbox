package com.slusarz.sandbox.springboot.sample.closeable.async;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AsyncTaskInitializer {

    private AsyncTask asyncTask;

    @PostConstruct
    public void init() throws InterruptedException {
        asyncTask.task();
    }

}
