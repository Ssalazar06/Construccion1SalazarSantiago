package app.adapters.order.entity;

import java.sql.Timestamp;

import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
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
@Table(name = "order")
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @JoinColumn(name="pet_id")
    @OneToOne
    private PetEntity pet;
    @JoinColumn(name =("person_Document"))
    @OneToOne
    private PersonEntity person;
    @JoinColumn(name = "user_id")
    @OneToOne
    private UserEntity user;
    @Column(name="medication_name")
    private String medicationName;
    @Column(name="medication_dosis")
    private double medicationDosis;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "date")
    private Timestamp date;
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
}
