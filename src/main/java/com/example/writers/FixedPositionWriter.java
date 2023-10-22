package com.example.writers;


import com.example.model.ProcessedData;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

@Component
public class FixedPositionWriter extends FlatFileItemWriter<ProcessedData> {

    public FixedPositionWriter(){
        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/txt/output.txt")));
        this.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("orderId, firstName, lastName, age, address, orderedDate, paymentMode, amount, deliveryDate");
            }
        });

        BeanWrapperFieldExtractor<ProcessedData> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"orderId", "firstName", "age", "orderedDate", "paymentMode", "amount"});

        FormatterLineAggregator<ProcessedData> aggregator = new FormatterLineAggregator<>();
        aggregator.setFieldExtractor(fieldExtractor);
        aggregator.setFormat("%5.5s%12.10s%d%12.10s%20.17s%8.3f");
        this.setLineAggregator(aggregator);

        this.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("Recorder at: " + LocalDateTime.now());
            }
        });
    }
}
