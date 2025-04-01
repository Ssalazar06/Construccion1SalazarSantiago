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

public class PersonEntity {
    
    @Id
    @Column(name = "document")
    private long document;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private long age;

}
