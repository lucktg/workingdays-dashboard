package com.tools.workingdaysdashboard.batch;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource({"classpath:/application.properties"})
public class BatchConfigurationTest {
    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public Resource resource() {
        return new ClassPathResource("batch/sample.txt");
    }

}
