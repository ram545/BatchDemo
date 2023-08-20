package com.example.readers;

import com.example.config.OrderDataFields;
import com.example.config.OrderDataRanges;
import com.example.model.OrdersData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FixedPositionReader extends FlatFileItemReader<OrdersData> {

    public FixedPositionReader(){

        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/txt/input.txt")));

        // Fixed Line Tokenizer to read fixed length files
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(OrderDataRanges.getCustomRanges());
        tokenizer.setNames(OrderDataFields.getFieldNames());

        // set target POJO class to copy data from file
        BeanWrapperFieldSetMapper<OrdersData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(OrdersData.class);

        DefaultLineMapper<OrdersData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        this.setLineMapper(lineMapper);
        this.setLinesToSkip(1);
    }
}
