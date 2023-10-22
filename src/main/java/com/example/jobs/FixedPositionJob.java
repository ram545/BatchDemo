package com.example.jobs;

import com.example.listener.ReadExceptionListener;
import com.example.model.ProcessedData;
import com.example.processors.FixedPositionProcessor;
import com.example.readers.FixedPositionReader;
import com.example.writers.FixedPositionWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileParseException;
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

    @Autowired
    private FixedPositionProcessor processor;

    @Autowired
    private ReadExceptionListener listener;

    @Bean(name = "Fixed Position Job")
    public Job FixedFileJob() throws FlatFileParseException{
        return jobBuilder.get("Fixed Length File Job")
                .incrementer(new RunIdIncrementer())
                .start(FixedFileStep())
                .build();
    }

    public Step FixedFileStep() throws FlatFileParseException {
        return stepBuilder.get("Fixed Position File Step")
                .<ProcessedData, ProcessedData>chunk(10)
                .listener(listener)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
