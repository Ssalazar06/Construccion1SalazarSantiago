package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class invoice {
    
    private long invioceId;
    private long petId;
    private long ownerId;
    private long orderId;
    private String item;
    private double amount;
    private double value;
    private Timestamp date;
    
    public invoice(long invioceId, long petId, long ownerId, long orderId, String item, double amount, double value,
            Timestamp date) {
        this.invioceId = invioceId;
        this.petId = petId;
        this.ownerId = ownerId;
        this.orderId = orderId;
        this.item = item;
        this.amount = amount;
        this.value = value;
        this.date = date;
    }
    
}
