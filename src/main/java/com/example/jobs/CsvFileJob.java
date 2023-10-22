package com.example.jobs;

import com.example.model.MlbPlayerData;
import com.example.processors.CsvFileProcessor;
import com.example.readers.CsvFileReader;
import com.example.writers.CsvFileWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvFileJob {

    @Value("${batch.input.chunk-size}")
    private Integer chunkSize;
    @Autowired
    private JobBuilderFactory jobFactory;
    @Autowired
    private StepBuilderFactory stepFactory;
    @Autowired
    private CsvFileReader reader;
    @Autowired
    private CsvFileProcessor processor;
    @Autowired
    private CsvFileWriter writer;

    @Bean(name="Csv File Job")
    public Job csvFileJob(){
        return jobFactory.get("Csv Job")
                .start(csvChunkStep())
                .build();
    }

    public Step csvChunkStep(){
        return stepFactory.get("Csv Chunk Step")
                .<MlbPlayerData, MlbPlayerData>chunk(chunkSize)
                .reader(reader.getCsvFileReader())
                .processor(processor)
                .writer(writer.getCsvFileWriter())
                .build();
    }

}
