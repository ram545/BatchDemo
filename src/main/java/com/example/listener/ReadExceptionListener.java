package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
@Slf4j
public class ReadExceptionListener implements ItemReadListener<Object> {
    @Override
    public void beforeRead() {
        log.info("Before Read");
    }

    @Override
    public void afterRead(Object o) {
        log.info("After Read");
    }

    @Override
    public void onReadError(Exception e) {
        if(e instanceof FlatFileParseException){
            log.info("Exception while file parse");
        }

        if(e instanceof FileNotFoundException){
            log.info("Exception file not found");
        }

        if(e instanceof NullPointerException){
            log.info("Unexpected exception occured terminate");
        }
    }
}
