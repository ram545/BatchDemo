package com.example.writers;

import com.example.model.PetalData;
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
public class JsontoFlatFileWriter extends FlatFileItemWriter<PetalData> {

        public JsontoFlatFileWriter(){

            this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/json/output.csv")));

            this.setHeaderCallback(new FlatFileHeaderCallback() {
                @Override
                public void writeHeader(Writer writer) throws IOException {
                    writer.write("Sepal Length, Sepal Width, Petal Length, Petal Width, Species");
                }
            });

            BeanWrapperFieldExtractor<PetalData> fieldExtractor = new BeanWrapperFieldExtractor<>();
            fieldExtractor.setNames(new String[]{"sepalLength", "sepalWidth", "petalLength", "petalWidth", "species"});

            DelimitedLineAggregator<PetalData> aggregator = new DelimitedLineAggregator<>();
            aggregator.setFieldExtractor(fieldExtractor);

            this.setLineAggregator(aggregator);
        }
}
