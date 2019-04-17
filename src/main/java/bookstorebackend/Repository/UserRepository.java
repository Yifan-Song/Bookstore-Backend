package bookstorebackend.Repository;

import bookstorebackend.Entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer >{
    List<UserEntity>findAll();

    UserEntity findByUserid(Integer id);

    UserEntity findByUsername(String name);

    void deleteByUserid(Integer id);

}
