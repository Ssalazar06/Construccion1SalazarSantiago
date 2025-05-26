package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class Invoice {
    
    private long invoiceId;
    private Order order;
    private String item;
    private double amount;
    private long medicationDosis;
    private Timestamp date;

    public Invoice(long invoiceId, Order order, String item, double amount, long medicationDosis, Timestamp date) {
        this.invoiceId = invoiceId;
        this.order = order;
        this.item = item;
        this.amount = amount;
        this.medicationDosis = medicationDosis;
        this.date = date;
    }
}
