package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InvoiceRequest {
    private long orderId;
    private double amount;
    private long medicationDosis;
    private String item;
}
