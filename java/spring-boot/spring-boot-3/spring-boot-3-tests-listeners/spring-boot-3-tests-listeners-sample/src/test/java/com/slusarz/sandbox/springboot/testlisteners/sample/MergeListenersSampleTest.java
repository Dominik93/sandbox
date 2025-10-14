package com.slusarz.sandbox.springboot.testlisteners.sample;

import com.slusarz.sandbox.springboot.testlisteners.sample.listeners.SampleTestListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners(value = { SampleTestListener.class },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class MergeListenersSampleTest {
    @Autowired
    SampleService sampleService;

    @Test
    void shouldTest() {
        assertThat(sampleService.get()).isEqualTo("OK");
    }
}
