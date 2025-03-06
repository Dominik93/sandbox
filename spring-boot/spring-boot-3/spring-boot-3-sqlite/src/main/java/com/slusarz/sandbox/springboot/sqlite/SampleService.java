package com.slusarz.sandbox.springboot.sqlite;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SampleService {

    private SampleRepository sampleRepository;

    @Transactional
    public void save(SampleEntity sampleEntity) {
        sampleRepository.save(sampleEntity);
    }

    public SampleEntity get(String id) {
        return sampleRepository.findById(id).orElseThrow();
    }

}
