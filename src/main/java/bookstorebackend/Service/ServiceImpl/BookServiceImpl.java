package bookstorebackend.Service.ServiceImpl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookstorebackend.Entity.BookEntity;
import bookstorebackend.Repository.BookRepository;
import bookstorebackend.Service.BookService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    public BookEntity findByBookid(int id){
        return bookRepository.findByBookid(id);
    }

    public JSONObject addBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, String book_abstract, String author_abstract, String isbn)
    {
        BookEntity newBook = new BookEntity();
        newBook.setBookpath(bookpath);
        newBook.setBookname(bookname);
        newBook.setBookid(bookid);
        newBook.setPrice(price);
        newBook.setAuthor(author);
        newBook.setYear(year);
        newBook.setIsbn(isbn);
        newBook.setBookAbstract(book_abstract);
        newBook.setAuthorAbstract(author_abstract);
        return JSONObject.fromObject(bookRepository.save(newBook));
    }

    public void deleteBookByBookid(int id)
    {
        System.out.println("Deleting");
        System.out.println(id);
        bookRepository.deleteByBookid(id);
    }

    public void deleteBook(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, String book_abstract, String author_abstract, String isbn)
    {
        BookEntity newBook = new BookEntity();
        newBook.setBookpath(bookpath);
        newBook.setBookname(bookname);
        newBook.setBookid(bookid);
        newBook.setPrice(price);
        newBook.setAuthor(author);
        newBook.setYear(year);
        newBook.setIsbn(isbn);
        newBook.setBookAbstract(book_abstract);
        newBook.setAuthorAbstract(author_abstract);
        bookRepository.delete(newBook);
    }

}