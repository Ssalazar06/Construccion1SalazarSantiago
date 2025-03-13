package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class petOwner extends user {
    private long petOwnerId;

    public petOwner(long personId, String personName, long personAge, long userId, String userName, String password,
            String role, boolean status, long petOwnerId) {
        super(personId, personName, personAge, userId, userName, password, role, status);
        this.petOwnerId = petOwnerId;
    }

    public petOwner(long userId, String userName, String password, String role, boolean status, long petOwnerId) {
        super(userId, userName, password, role, status);
        this.petOwnerId = petOwnerId;
    }

    public petOwner(long petOwnerId) {
        this.petOwnerId = petOwnerId;
    }
}
