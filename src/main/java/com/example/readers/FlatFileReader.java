package com.example.readers;

import com.example.model.MlbPlayerData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FlatFileReader extends FlatFileItemReader<MlbPlayerData> {

    public FlatFileReader(){

        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/csv/mlb_players.csv")));
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
        tokenizer.setNames("Name", "Team", "Position", "Height", "Weight", "Age");

        BeanWrapperFieldSetMapper<MlbPlayerData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(MlbPlayerData.class);

        DefaultLineMapper<MlbPlayerData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        this.setLineMapper(lineMapper);
        this.setLinesToSkip(1);
    }
}
