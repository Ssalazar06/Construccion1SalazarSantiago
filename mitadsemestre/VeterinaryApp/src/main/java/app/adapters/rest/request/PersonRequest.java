package app.adapters.rest.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {

    private long Document;
    private String Name;
    private long Age;

    public String toString(){
        return "PersonRequest{" +
                "personDocument=" + Document +
                ", personName='" + Name + '\'' +
                ", personAge=" + Age +
                '}';
    }
}