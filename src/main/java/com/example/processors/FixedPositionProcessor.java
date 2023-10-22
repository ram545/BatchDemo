package com.example.processors;

import com.example.model.ProcessedData;
import com.sun.istack.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class FixedPositionProcessor implements ItemProcessor<ProcessedData, ProcessedData> {


    @Override
    public ProcessedData process(@NotNull ProcessedData orders) throws Exception {
        //System.out.println(orders.toString());
        return orders;
    }
}
