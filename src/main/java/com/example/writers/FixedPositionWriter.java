package com.example.writers;


import com.example.model.OrdersData;
import org.hibernate.criterion.Order;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

@Component
public class FixedPositionWriter extends FlatFileItemWriter<OrdersData> {

    public FixedPositionWriter(){
        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/csv/output.csv")));
        this.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("orderId, firstName, lastName, age, address, orderedDate, paymentMode, amount, deliveryDate");
            }
        });

        BeanWrapperFieldExtractor<OrdersData> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"orderId", "firstName", "lastName", "age", "address", "orderedDate", "paymentMode", "amount", "deliveryDate"});

        DelimitedLineAggregator<OrdersData> aggregator = new DelimitedLineAggregator<>();
        aggregator.setFieldExtractor(fieldExtractor);

        this.setLineAggregator(aggregator);

        this.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("Recorder at: " + LocalDateTime.now());
            }
        });
    }
}
