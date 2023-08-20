package com.example.config;


import org.springframework.batch.item.file.transform.Range;

public enum OrderDataRanges {
    OrderId(new Range(1,10)),
    FirstName(new Range(11,22)),
    LastName(new Range(23,34)),
    Age(new Range(35,37)),
    Address(new Range(38, 83)),
    OrderedDate(new Range(84,96)),
    PaymentMode(new Range(97, 121)),
    TotalAmount(new Range(122,127)),
    DeliveryDate(new Range(128,138));


    private final Range range;
    OrderDataRanges(Range range) {
        this.range = range;
    }

    public static Range[] getCustomRanges(){
        return new Range[]{OrderId.getRange(),
                            FirstName.getRange(),
                            LastName.getRange(),
                            Age.getRange(),
                            Address.getRange(),
                            OrderedDate.getRange(),
                            PaymentMode.getRange(),
                            TotalAmount.getRange(),
                            DeliveryDate.getRange()};
    }

    private Range getRange() {
        return this.range;
    }
}
