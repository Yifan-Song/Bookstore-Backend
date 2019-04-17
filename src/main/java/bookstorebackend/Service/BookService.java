package bookstorebackend.Service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import bookstorebackend.Entity.BookEntity;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface BookService {

    public List<BookEntity> findAll();

    public BookEntity findByBookid(int id);

    public JSONObject addBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, String book_abstract, String author_abstract, String isbn);

    public void deleteBookByBookid(int id);

    public void deleteBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, String book_abstract, String author_abstract, String isbn);

}