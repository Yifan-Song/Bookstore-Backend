package BookStoreBackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Repository.BookRepository;


public interface BookDetailService {
    public String findImageByBookid(Integer bookid);
}
