package BookStoreBackEnd.Service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    public BookEntity findByBookid(int id){
        return bookRepository.findByBookid(id);
    }

    public JSONObject addBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid)
    {
        BookEntity newBook = new BookEntity();
        newBook.setBookpath(bookpath);
        newBook.setBookname(bookname);
        newBook.setBookid(bookid);
        newBook.setPrice(price);
        newBook.setAuthor(author);
        newBook.setYear(year);
        return JSONObject.fromObject(bookRepository.save(newBook));
    }

    public void deleteBookByBookid(int id)
    {
        System.out.println("Deleting");
        System.out.println(id);
        bookRepository.deleteByBookid(id);
    }

    public void deleteBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid)
    {
        BookEntity newBook = new BookEntity();
        newBook.setBookpath(bookpath);
        newBook.setBookname(bookname);
        newBook.setBookid(bookid);
        newBook.setPrice(price);
        newBook.setAuthor(author);
        newBook.setYear(year);
        System.out.println(newBook.toString());
        bookRepository.delete(newBook);
    }

}