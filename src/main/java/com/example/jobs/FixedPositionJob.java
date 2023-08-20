package com.example.jobs;

import com.example.model.OrdersData;
import com.example.readers.FixedPositionReader;
import com.example.writers.FixedPositionWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FixedPositionJob {

    @Autowired
    private JobBuilderFactory jobBuilder;

    @Autowired
    private StepBuilderFactory stepBuilder;

    @Autowired
    private FixedPositionReader reader;

    @Autowired
    private FixedPositionWriter writer;

    @Bean
    public Job FixedFileJob(){
        return jobBuilder.get("Fixed Length File Job")
                .start(FixedFileStep())
                .build();
    }

    public Step FixedFileStep(){
        return stepBuilder.get("Fixed Position File Step")
                .<OrdersData, OrdersData>chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
