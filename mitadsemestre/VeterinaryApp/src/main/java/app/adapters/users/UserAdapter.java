package app.adapters.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.person.entity.PersonEntity;
import app.adapters.users.entity.UserEntity;
import app.adapters.users.repository.UserRepository;
import app.domain.models.User;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class UserAdapter implements UserPort{
    @Autowired
    public UserRepository userRepository;

    public User user;

    @Override
    public User findByUserName(User user) throws Exception{
        UserEntity userEntity = userRepository.findByUserName(user.getUserName());
        if(userEntity == null){
            throw new Exception("No existe un usuario con ese nombre");
        }
        return userAdapter(userEntity);
    }


    @Override
    public boolean existUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public void saveUser(User user){
        System.out.println("datos que llegan al user adapter: " + user.getName() + " " + user.getDocument());
        UserEntity userEntity = userAdapter(user);
        System.out.println("datos antes de ser guardados: "+ userEntity.getUserName() + " " + userEntity.getPerson().getDocument());
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }
    

    public User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setDocument(userEntity.getPerson().getDocument());
        user.setName(userEntity.getPerson().getName());
        user.setAge(userEntity.getPerson().getAge());
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity userAdapter(User user){
        //reemplazo metodo personAdpter por mala implementación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(user.getName());
        personEntity.setDocument(user.getDocument());
        personEntity.setAge(user.getAge());

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }


    @Override
    public User findByPersonDocument(long Document) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPersonDocument'");
    }
}