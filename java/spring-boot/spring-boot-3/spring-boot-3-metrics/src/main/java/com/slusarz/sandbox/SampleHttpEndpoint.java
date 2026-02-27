package com.slusarz.sandbox;

import com.slusarz.sandbox.custom.counter.CounterService;
import com.slusarz.sandbox.custom.gauge.GaugeService;
import com.slusarz.sandbox.custom.timer.TimerService;
import com.slusarz.sandbox.datasource.SampleEntity;
import com.slusarz.sandbox.datasource.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@Slf4j
@RestController
public class SampleHttpEndpoint {

    @Autowired
    private RestClient client;
    @Autowired
    private SampleRepository sampleRepository;
    @Autowired
    private CounterService counterService;
    @Autowired
    private TimerService timerService;
    @Autowired
    private GaugeService gaugeService;

    @GetMapping("/rest-client")
    String restClient() {
        return client.get().uri("https://jsonplaceholder.typicode.com/posts/1").retrieve().toEntity(String.class).getBody();
    }

    @GetMapping("/datasource/{id}")
    String datasource(@PathVariable("id") String id) {
        SampleEntity save = sampleRepository.save(new SampleEntity(id));
        return save.getId();
    }

    @GetMapping("/counter")
    void counter() {
        counterService.count();
    }

    @GetMapping("/timer")
    void timer() {
        timerService.timer();
    }

    @GetMapping("/gauge-add")
    void gaugeAdd() {
        gaugeService.gaugeAdd();
    }

    @GetMapping("/gauge-delete")
    void gaugeDelete() {
        gaugeService.gaugeDelete();
    }


}
