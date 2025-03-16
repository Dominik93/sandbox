package com.slusarz.sandbox.springboot.timezone;

import jakarta.persistence.EntityManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class DateTimeHttpEndpoint {

    @Autowired
    private DateTimeRepository dateTimeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * curl -d '{"id":"1","localTime":"19:00:00.0000000","offsetTime":"19:00:00.0000000+01:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/time
     *
     * @return
     */
    @PostMapping("/time")
    public TimeEntity saveTime(@RequestBody TimeRequest request) {
        TimeEntity timeEntity = new TimeEntity(request.getId(), request.getLocalTime(), request.getOffsetTime());
        log.info("Save {}", timeEntity);
        timeRepository.save(timeEntity);
        return timeEntity;
    }

    /**
     * curl -d '{"id":"1","dateTime":"2025-03-12T19:00:00.0000000","zonedDateTime":"2025-03-12T19:00:00.0000000+01:00"}' -H "Content-Type: application/json" -X POST http://localhost:8080/date-time
     *
     * @return
     */
    @PostMapping("/date-time")
    public DateTimeEntity saveDateTime(@RequestBody DateTimeRequest request) {
        DateTimeEntity dateTimeEntity = new DateTimeEntity(request.getId(), request.getDateTime(), request.getZonedDateTime());
        log.info("Save {}", dateTimeEntity);
        dateTimeRepository.save(dateTimeEntity);
        return dateTimeEntity;
    }

    /**
     * curl -X POST http://localhost:8080/date-time/current/1
     *
     * @return
     */
    @PostMapping("/date-time/current/{id}")
    public DateTimeEntity saveCurrentDateTime(@PathVariable("id") String id) {
        DateTimeEntity dateTimeEntity = new DateTimeEntity(id, LocalDateTime.now(), ZonedDateTime.now());
        log.info("Save {}", dateTimeEntity);
        dateTimeRepository.save(dateTimeEntity);
        return dateTimeEntity;
    }

    /**
     * curl -X GET http://localhost:8080/time/id
     */
    @GetMapping("/time/{id}")
    public TimeEntity getTime(@PathVariable("id") String id) {
        return timeRepository.findById(id).orElseThrow();
    }

    /**
     * curl -X GET http://localhost:8080/date-time/1
     */
    @GetMapping("/date-time/{id}")
    public DateTimeEntity getDateTime(@PathVariable("id") String id) {
        return dateTimeRepository.findById(id).orElseThrow();
    }

    /**
     * curl -X GET http://localhost:8080/date-time/raw
     */
    @GetMapping("/date-time/raw")
    public List<Object[]> getRawDateTime() {
        List<Object[]> resultList =
                entityManager.createNativeQuery("select ID, LOCAL_DATE_TIME, ZONED_DATE_TIME from DATE_TIME").getResultList();
        for (Object[] objects : resultList) {
            log.info("Result {}", Arrays.stream(objects).map(Object::toString).toList());
        }
        return resultList;
    }

    /**
     * curl -X GET http://localhost:8080/time/raw
     */
    @GetMapping("/time/raw")
    public List<Object[]> getRawTime() {
        List<Object[]> resultList =
                entityManager.createNativeQuery("select ID, LOCAL_DATE_TIME, ZONED_DATE_TIME from TIME").getResultList();
        for (Object[] objects : resultList) {
            log.info("Result {}", Arrays.stream(objects).map(Object::toString).toList());
        }
        return resultList;
    }

    @Data
    public static class TimeRequest {

        private String id;

        private LocalTime localTime;

        private OffsetTime offsetTime;

    }

    @Data
    public static class DateTimeRequest {

        private String id;

        private LocalDateTime dateTime;

        private ZonedDateTime zonedDateTime;

    }

}
