package app.ports;

import app.domain.models.user;

public interface userPort {
    
    public boolean existUserName(String userName);
    public void saveUser(user user);
    public user findByPersonId(long personId);

}
