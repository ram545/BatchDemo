package com.example.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "orders_data")
public class OrdersData {

    @Column
    @Id
    @NonNull
    private String orderId;
    @Column
    @NonNull
    private String firstName;
    @Column
    @NonNull
    private String lastName;
    @Column
    @NonNull
    private Integer age;
    @Column
    @NonNull
    private String address;
    @Column
    @NonNull
    private String orderedDate;
    @Column
    @NonNull
    private String paymentMode;
    @Column
    @NonNull
    private Double amount;
    @Column
    @NonNull
    private String deliveryDate;

}
