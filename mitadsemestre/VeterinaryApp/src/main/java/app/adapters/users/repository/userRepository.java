package app.adapters.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.users.entity.userEntity;

public interface userRepository extends JpaRepository<userEntity, Long>{
    
    public boolean existByUserName(String userName);
    public userEntity findByPersonDocument(long personDocument);
    
}
