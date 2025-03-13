package app.adapters.persons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor

public class personEntity {
    
    @Id
    @Column(name = "document")
    private long document;
    @Column(name = "person_name")
    private String personName;
    @Column(name = "person_Age")
    private long personAge;

}
