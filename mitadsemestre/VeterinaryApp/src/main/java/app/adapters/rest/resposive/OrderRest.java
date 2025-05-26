package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRest {
    private long orderId;
    private PetRest pet;
    private PersonRest owner;
    private PersonRest veterinarian;
    private String medicationName;
    private double medicationDosis;
    private String orderStatus;
    private Timestamp date;
    @Override
    public String toString() {
        return "OrderRest [orderId=" + orderId + ", pet=" + pet + ", owner=" +  ", veterinarian=" + veterinarian
                + ", medicationName=" + medicationName + ", medicationDosis=" + medicationDosis + ", orderStatus="
                + orderStatus + ", date=" + date + "]";
    }

    
}
