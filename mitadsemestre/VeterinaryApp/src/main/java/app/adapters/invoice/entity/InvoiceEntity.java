package app.adapters.invoice.entity;

import java.sql.Timestamp;

import app.adapters.order.entity.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice")
@Setter
@Getter
@NoArgsConstructor

public class InvoiceEntity{
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;
    @JoinColumn(name="order_id")
    @OneToOne
    private OrderEntity order;
    @Column(name="amount")
    private double amount;
    @Column(name="medication_quantity")
    private long medicationQuantity;
    @Column(name ="item")
    private String item;
    @Column(name="date")
    private Timestamp date;
    public Object getProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProduct'");
    }
}
