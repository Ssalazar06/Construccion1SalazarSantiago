package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Person {

    private long Document;
    private String Name;
    private long Age;
    
    public Person(long document, String name, long age) {
        Document = document;
        Name = name;
        Age = age;
    }

}
