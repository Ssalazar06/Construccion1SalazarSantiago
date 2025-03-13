package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class order {

    private long orderId;
    private String item;
    private Timestamp date;
    
    public order(long orderId, String item, Timestamp date) {
        this.orderId = orderId;
        this.item = item;
        this.date = date;
    }
    
}
