package com.example.readers;

import com.example.model.MlbPlayerData;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CsvFileReader{
    @Value("${batch.csv.input.path}")
    private String inputPath;
    @Value("${batch.csv.input.fieldNames}")
    private String[] fieldNames;

    public ItemReader<MlbPlayerData> getCsvFileReader(){

        BeanWrapperFieldSetMapper<MlbPlayerData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(MlbPlayerData.class);

        return new FlatFileItemReaderBuilder<MlbPlayerData>()
                .name("Csv Flat File Item Reader")
                .resource(new FileSystemResource(new File(inputPath)))
                .delimited()
                .names(fieldNames)
                .fieldSetMapper(fieldSetMapper)
                .linesToSkip(1)
                .strict(false)
                .build();
    }
}
