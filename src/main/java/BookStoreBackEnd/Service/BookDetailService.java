package BookStoreBackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Repository.BookRepository;

@Service
public class BookDetailService {
    @Autowired
    private BookRepository bookRepository;

    public String findImageByBookid(Integer bookid){
        BookEntity image = bookRepository.findByBookid(bookid);
        String path = image.getBookpath();
        return path;
    }
}
