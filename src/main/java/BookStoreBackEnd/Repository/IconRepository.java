package BookStoreBackEnd.Repository;

import java.util.List;

import BookStoreBackEnd.Entity.IconEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.math.BigInteger;

public interface IconRepository extends MongoRepository<IconEntity, BigInteger>{

    IconEntity findByUserid(Integer id);

}