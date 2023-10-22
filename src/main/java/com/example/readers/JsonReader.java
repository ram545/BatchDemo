package com.example.readers;

import com.example.model.PetalData;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JsonReader extends JsonItemReader<PetalData> {

    public JsonReader(@Value("${batch.input.json}") String inputPath){
        System.out.println(inputPath);
        this.setJsonObjectReader(new JacksonJsonObjectReader<PetalData>(PetalData.class));
        this.setResource(new FileSystemResource(new File("/home/leopard/Projects/BatchDemo/src/main/resources/InputFiles/json/input.json")));

    }
}
