package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class user extends person{
    private long userId;
    private String userName;
    private String password;
    private String role;
    private boolean status;
    
    public user(long personId, String personName, long personAge, long userId, String userName, String password,
            String role, boolean status) {
        super(personId, personName, personAge);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    public user(long userId, String userName, String password, String role, boolean status) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
}
