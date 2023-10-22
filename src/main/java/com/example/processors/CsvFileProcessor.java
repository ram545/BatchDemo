package com.example.processors;

import com.example.model.MlbPlayerData;
import com.sun.istack.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvFileProcessor implements ItemProcessor<MlbPlayerData, MlbPlayerData> {


    @Override
    public MlbPlayerData process(@NotNull MlbPlayerData orders) throws Exception {
        if(orders.getAge() < 31 && orders.getHeight() > 70)
            return orders;
        else
            return null;
    }
}
