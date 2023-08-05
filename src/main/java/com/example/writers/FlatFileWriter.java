package com.example.writers;

import com.example.model.MlbPlayerData;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

@Component
public class FlatFileWriter extends FlatFileItemWriter<MlbPlayerData> {

    public FlatFileWriter(){

        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/csv/output.csv")));

        this.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("Name, Team, Position, Height(Inches), Weight(Lbs), Age");
            }
        });

        BeanWrapperFieldExtractor<MlbPlayerData> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"name", "team", "position", "height", "weight", "age"});

        DelimitedLineAggregator<MlbPlayerData> aggregator = new DelimitedLineAggregator<>();
        aggregator.setFieldExtractor(fieldExtractor);

        this.setLineAggregator(aggregator);
    }
}
