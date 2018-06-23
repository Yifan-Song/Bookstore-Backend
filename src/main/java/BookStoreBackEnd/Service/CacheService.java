package BookStoreBackEnd.Service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Entity.BookcacheEntity;
import BookStoreBackEnd.Repository.CacheRepository;
import BookStoreBackEnd.Repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CacheService {

    public List<BookcacheEntity> GetCartByUserid(int userid);

    public List<BookcacheEntity> GetOrdersByUserid(int userid);

    public JSONObject addCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number);

    public JSONObject updateCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

    public JSONObject addOrderitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

    public void deleteCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

}