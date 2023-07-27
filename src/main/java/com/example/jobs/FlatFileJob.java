package com.example.jobs;

import com.example.model.MlbPlayerData;
import com.example.readers.FlatFileReader;
import com.example.writers.FlatFileWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;

@Configuration
public class FlatFileJob {

    @Autowired
    private JobBuilderFactory jobFactory;

    @Autowired
    private StepBuilderFactory stepFactory;

    @Autowired
    private FlatFileReader csvReader;

    @Autowired
    private FlatFileWriter csvWriter;

    @Bean
    public Job csvFileJob(){
        return jobFactory.get("Flat File Batch Job")
                .incrementer(new JobParametersIncrementer() {
                    @Override
                    public JobParameters getNext(JobParameters jobParameters) {
                        HashMap<String, JobParameter> params = new HashMap<>();
                        params.put("time", new JobParameter(System.currentTimeMillis()));
                        return new JobParameters(params);
                    }
                })
                .start(csvChunkStep())
                .build();
    }

    public Step csvChunkStep(){
        return stepFactory.get("CSV Chunk Step")
                .<MlbPlayerData, MlbPlayerData>chunk(10)
                .reader(csvReader)
                .writer(csvWriter)
                .build();
    }

}
