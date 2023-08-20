package com.example.config;

public final class OrderDataFields {
    private static final String OrderId = "orderId";
    private static final String FirstName = "firstName";
    private static final String LastName = "lastName";
    private static final String Age = "age";
    private static final String Address = "address";
    private static final String OrderedDate = "orderedDate";
    private static final String PaymentMode = "paymentMode";
    private static final String TotalAmount = "amount";
    private static final String DeliveryDate = "deliveryDate";

    public static String[] getFieldNames(){
        return new String[]{OrderId, FirstName, LastName, Age, Address, OrderedDate,
                PaymentMode, TotalAmount, DeliveryDate};
    }
}
