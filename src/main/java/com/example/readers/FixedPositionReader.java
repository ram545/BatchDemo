package com.example.readers;

import com.example.model.ProcessedData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Map;

@Component
public class FixedPositionReader extends FlatFileItemReader<ProcessedData> {

    @Value("${batch.input.path}")
    private String path;
    @Value("${batch.input.fieldNames}")
    private String[] fieldNames;
    @Value("#{${batch.input.ranges}}")
    private Map<Integer, Integer> valuesMap;

    public Range[] getCustomRanges(){
        Range[] rs = new Range[valuesMap.size()];
        int iter = 0;
        for (Map.Entry<Integer,Integer> mp : valuesMap.entrySet()) {
            rs[iter++] = new Range(mp.getKey(), mp.getValue());
        }
        return rs;
    }

    public static class EmployeeFieldSetMapper implements FieldSetMapper<ProcessedData> {
        @Override
        public ProcessedData mapFieldSet(FieldSet fieldSet) throws BindException {
            ProcessedData orderData = new ProcessedData();
            orderData.setOrderId(fieldSet.readString("orderId"));
            orderData.setFirstName(fieldSet.readString("firstName"));
            orderData.setAge(fieldSet.readInt("age"));
            orderData.setOrderedDate(fieldSet.readString("orderedDate"));
            orderData.setPaymentMode(fieldSet.readString("paymentMode"));
            orderData.setAmount(fieldSet.readDouble("amount"));
            return orderData;
        }
    }
    public FixedPositionReader(){
    }

    @PostConstruct
    public void init(){
        this.setResource(new FileSystemResource(new File(path)));

        // Fixed Line Tokenizer to read fixed length files
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(getCustomRanges());
        tokenizer.setNames(fieldNames);

        // set target POJO class to copy data from file
        EmployeeFieldSetMapper fieldSetMapper = new EmployeeFieldSetMapper();

        DefaultLineMapper<ProcessedData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        this.setLineMapper(lineMapper);
        this.setLinesToSkip(1);
    }
}
