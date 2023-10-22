package com.example.model;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "processed_data")
public class ProcessedData {

    @Column
    @Id
    @NonNull
    private String orderId;
    @Column
    @NonNull
    private String firstName;

    @Column
    @NonNull
    private Integer age;

    @Column
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String orderedDate;

    @Column
    @NonNull
    private String paymentMode;

    @Column
    @NonNull
    private Double amount;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ProcessedData that = (ProcessedData) o;
        return Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
