package com.example.jobs;

import com.example.model.OrdersData;
import com.example.processors.FixedPositionProcessor;
import com.example.readers.FixedPositionReader;
import com.example.writers.FixedPositionWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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

    @Autowired
    private FixedPositionProcessor processor;

    @Bean
    public Job FixedFileJob(){
        return jobBuilder.get("Fixed Length File Job")
                .incrementer(new RunIdIncrementer())
                .start(FixedFileStep())
                .build();
    }

    public Step FixedFileStep(){
        return stepBuilder.get("Fixed Position File Step")
                .<OrdersData, OrdersData>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
