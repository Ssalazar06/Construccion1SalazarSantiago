package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Order {

    private long orderId;
    private Pet pet;
    private Person owner;
    private User veterinarian;
    private String medicationName;
    private double medicationDosis;
    private String orderStatus;
    private Timestamp date;

    public Order(long orderId, Pet pet, Person owner, User veterinarian, String medicationName, double medicationDosis,
            String orderStatus, Timestamp date) {
        this.orderId = orderId;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medicationName = medicationName;
        this.medicationDosis = medicationDosis;
        this.orderStatus = orderStatus;
        this.date = date;
    }
}
