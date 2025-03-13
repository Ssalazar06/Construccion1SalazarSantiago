package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class person {
    private long personId;
    private long document;
    private String personName;
    private long personAge;

    public person(long personId, String personName, long personAge) {
        this.personId = personId;
        this.personName = personName;
        this.personAge = personAge;
    }  
}
