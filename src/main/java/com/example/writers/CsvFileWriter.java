package com.example.writers;

import com.example.model.MlbPlayerData;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

@Component
public class CsvFileWriter{

    @Value("${batch.csv.output.path}")
    private String outputPath;
    @Value("${batch.csv.input.fieldNames}")
    private String[] fieldNames;
    public ItemWriter<MlbPlayerData> getCsvFileWriter(){
        return new FlatFileItemWriterBuilder<MlbPlayerData>()
                .name("Csv Flat File Item Writer")
                .resource(new FileSystemResource(new File(outputPath)))
                .headerCallback(new FlatFileHeaderCallback() {
                    @Override
                    public void writeHeader(Writer writer) throws IOException {
                        writer.write("Name, Team, Position, Height(Inches), Weight(Lbs), Age");
                    }
                })
                .delimited()
                .names(fieldNames)
                .build();
    }
}
