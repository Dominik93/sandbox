package com.slusarz.sandbox.springboot.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SampleRepositoryFirstTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    void shouldSaveSample() {
        sampleRepository.save(new SampleEntity("1"));

        Optional<SampleEntity> sample = sampleRepository.findById("1");
        assertThat(sample).isPresent();
    }

    @Test
    void shouldGetSample() {
        Optional<SampleEntity> sample = sampleRepository.findById("1");

        assertThat(sample).isNotNull();
    }

    @Test
    void shouldTryDeleteSample() {
        sampleRepository.deleteById("1");
    }

}