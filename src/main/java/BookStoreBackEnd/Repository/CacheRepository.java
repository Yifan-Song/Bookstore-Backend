package BookStoreBackEnd.Repository;

import BookStoreBackEnd.Entity.BookcacheEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CacheRepository  extends JpaRepository<BookcacheEntity, Integer >{

    List<BookcacheEntity>findByUseridAndStatus(int userid, int status);

    BookcacheEntity findByUseridAndBookidAndStatus(int userid, int bookid, int status);

    @Modifying
    @Query(value = "update bookcache set status = :status where cacheid = :cacheid",nativeQuery = true)
    void updateStatusById(@Param("cacheid") Integer cacheid, @Param("status") Integer status);

    @Modifying
    @Query(value = "update bookcache set ordertime = :ordertime where cacheid = :cacheid",nativeQuery = true)
    void updateOrdertimeById(@Param("cacheid") Integer cacheid, @Param("ordertime") String ordertime);

    @Modifying
    @Query(value = "update bookcache set paytime = :paytime where cacheid = :cacheid",nativeQuery = true)
    void updatePaytimeById(@Param("cacheid") Integer cacheid, @Param("paytime") String paytime);

    @Modifying
    @Query(value = "update bookcache set number = :number where cacheid = :cacheid",nativeQuery = true)
    void updataNumberById(@Param("cacheid") Integer cacheid, @Param("number") Integer number);


}
