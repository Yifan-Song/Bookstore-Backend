package BookStoreBackEnd.Service.ServiceImpl;

import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Service.BookDetailService;

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
