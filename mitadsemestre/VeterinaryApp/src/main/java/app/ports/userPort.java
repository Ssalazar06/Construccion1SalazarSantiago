package app.ports;

import app.domain.models.User;

public interface UserPort {
    
    public boolean existUserName(String userName)throws Exception;
    public void saveUser(User user)throws Exception;
    public User findByPersonDocument(long personDocument)throws Exception;
    public User findByUserName(User userName)throws Exception;
}
