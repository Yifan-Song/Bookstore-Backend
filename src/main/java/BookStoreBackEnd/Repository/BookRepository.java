package BookStoreBackEnd.Repository;

import BookStoreBackEnd.Entity.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer >{
    List<BookEntity>findAll();

    List<BookEntity>findBy();

    BookEntity findByBookid(Integer id);

    void deleteByBookid(int id);
}