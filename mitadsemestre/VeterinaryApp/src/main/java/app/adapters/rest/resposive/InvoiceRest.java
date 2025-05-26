package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InvoiceRest {
    private long invoiceId;
    private OrderRest order;
    private double amount;
    private long MedicationDosis;
    private String item;
    private Timestamp date;
}
