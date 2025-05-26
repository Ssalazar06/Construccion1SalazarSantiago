package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    private long orderId;
    private long petId;
    private long owner;
    private long veterinarianId;
    private String medicationName;
    private double medicationDosis;
    private String orderStatus;
}
