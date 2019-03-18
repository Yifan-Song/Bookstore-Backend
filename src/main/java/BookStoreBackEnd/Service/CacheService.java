package BookStoreBackEnd.Service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Entity.BookcacheEntity;
import BookStoreBackEnd.Repository.CacheRepository;
import BookStoreBackEnd.Repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface CacheService {
    List<BookcacheEntity> orderToPay = new ArrayList();

    public List<BookcacheEntity> GetCartByUserid(int userid);

    public List<BookcacheEntity> GetHistoryOrdersByUserid(int userid);

    public JSONObject addCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number);

    public JSONObject updateCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

    public JSONObject addOrderitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

    public List<BookcacheEntity> getOrder();

    public List<BookcacheEntity> lockOrder(String time);

    public String payOrder(String time, String price, int userid);

    public void deleteCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid);

}