package app.domain.models;

import app.adapters.users.UserAdapter;
import app.adapters.users.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class User extends Person{
    private long userId;
    private String userName;
    private String password;
    private String role;
    public Object setPerson;

    public User(long personDocument, String personName, long personAge, long userId, String userName, String password,
            String role) {
        super(personDocument, personName, personAge);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public User(long userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public User findByPersonDocument(UserAdapter userAdapter, Long personDocument) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPersonDocument'");
    }
}
