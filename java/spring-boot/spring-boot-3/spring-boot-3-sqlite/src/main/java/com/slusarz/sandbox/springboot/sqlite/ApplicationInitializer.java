package com.slusarz.sandbox.springboot.sqlite;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class ApplicationInitializer {

    private SampleService sampleService;

    @PostConstruct
    void init() {
        SampleEntity entity = save();
        read(entity.getId());
    }

    private void read(String id) {
        SampleEntity sampleEntity = sampleService.get(id);
        log.info("Read {}", sampleEntity);
    }

    private SampleEntity save() {
        SampleEntity entity = SampleEntity.builder()
                .id(UUID.randomUUID().toString())
                .number(1)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .dateTime(LocalDateTime.now())
                .build();
        sampleService.save(entity);
        log.info("Saved {}", entity);
        return entity;
    }
}
