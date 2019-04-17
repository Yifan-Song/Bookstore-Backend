package bookstorebackend.Service.ServiceImpl;

import bookstorebackend.Entity.BookEntity;
import bookstorebackend.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookstorebackend.Service.BookDetailService;

@Service
public class BookDetailServiceImpl implements BookDetailService{

    @Autowired
    private BookRepository bookRepository;

    public String findImageByBookid(Integer bookid){
        BookEntity image = bookRepository.findByBookid(bookid);
        String path = image.getBookpath();
        return path;
    }
}
