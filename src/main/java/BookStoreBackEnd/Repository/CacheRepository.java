package BookStoreBackEnd.Repository;

import BookStoreBackEnd.Entity.BookcacheEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository  extends JpaRepository<BookcacheEntity, Integer >{

    List<BookcacheEntity>findByUseridAndStatus(int userid, int status);

    BookcacheEntity findByBookidAndStatus(int bookid, int status);
}
