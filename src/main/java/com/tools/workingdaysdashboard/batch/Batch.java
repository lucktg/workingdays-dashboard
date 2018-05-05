package com.tools.workingdaysdashboard.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class Batch {

    @Bean
    public Job job(JobBuilderFactory factory, StepBuilderFactory stepBuilder, ReadStep readStep, Resource in, DataSource dataSource) throws Exception {
        TaskletStep step = stepBuilder.get("file-db")
                .<AttendanceRecord,AttendanceRecord>chunk(10)
                .reader(readStep.fileReader(in))
                .writer(readStep.writer(dataSource))
                .build();

        return factory.get("job").incrementer(new RunIdIncrementer()).start(step).build();
    }
}
