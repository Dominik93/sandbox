package com.slusarz.sandbox.cache;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@CacheConfig("sample")
public class SampleCache {

    @Cacheable
    public String get(String id) {
        return id + " -" + UUID.randomUUID();
    }

}
