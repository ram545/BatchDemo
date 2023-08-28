package com.example.processors;

import com.example.model.OrdersData;
import com.sun.istack.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class FixedPositionProcessor implements ItemProcessor<OrdersData, OrdersData> {


    @Override
    public OrdersData process(@NotNull OrdersData orders) throws Exception {

        return orders;
    }
}
