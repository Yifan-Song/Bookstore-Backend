package bookstorebackend.Service.ServiceImpl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import bookstorebackend.Entity.BookEntity;
import bookstorebackend.Entity.BookcacheEntity;
import bookstorebackend.Repository.CacheRepository;
import bookstorebackend.Repository.BookRepository;
import bookstorebackend.Service.CacheService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;


@Service
@Scope("prototype")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CacheServiceImpl implements CacheService {
    @Autowired
    private CacheRepository cacheRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public String decoder(String msg, int id){
        String newEncoded = "";
        char newChar;
        for(int i = 0; i < msg.length(); i++){
            int charNum = msg.charAt(i);
            newChar =  (char) (charNum - id);
            newEncoded += newChar;
        }
        System.out.println("newEncoded:"+newEncoded);
        byte[] encodeBase64 = Base64.decodeBase64(newEncoded);
        String decoded =  new String(encodeBase64);
        System.out.println("decoded:"+decoded);
        return decoded;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public List<BookcacheEntity> GetCartByUserid(int userid)
    {
        return cacheRepository.findByUseridAndStatus(userid, 0);//status=0:购物车；status=1:待支付；status2:已支付
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<BookcacheEntity> GetHistoryOrdersByUserid(int userid)
    {
        return cacheRepository.findByUseridAndStatus(userid, 2);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public JSONObject addCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number)
    {
        if(bookRepository.findByBookid(bookid).getStock() <= 0)
        {
            JSONObject jo = new JSONObject();
            jo.put("message","Stock too small");
            return jo;
        }

        BookcacheEntity bookCache = cacheRepository.findByUseridAndBookidAndStatus(userid, bookid, 0);
        if(bookCache != null) {
            int cacheid = bookCache.getCacheid();
            int oldnum = bookCache.getNumber();
            cacheRepository.updataNumberById(cacheid, oldnum + number);
            String msg = "{\"" + bookCache.getBookname() +"\":\"numberUpdated\"}";
            return JSONObject.fromObject(msg);
        }

        BookcacheEntity newCache = new BookcacheEntity();
        newCache.setUserid(userid);
        newCache.setCacheid(0);
        newCache.setStatus(0);
        newCache.setNumber(number);
        newCache.setBookname(bookname);
        newCache.setBookpath(bookpath);
        newCache.setBookid(bookid);
        newCache.setPrice(price);
        newCache.setAuthor(author);
        newCache.setYear(year);

        return JSONObject.fromObject(cacheRepository.save(newCache));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public JSONObject updateCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid)
    {
        if(bookRepository.findByBookid(bookid).getStock() < number)
        {
            JSONObject jo = new JSONObject();
            jo.put("message","Stock too small");
            return jo;
        }
        BookcacheEntity newCache = new BookcacheEntity();
        newCache.setUserid(userid);
        newCache.setCacheid(cacheid);
        newCache.setStatus(0);
        newCache.setNumber(number);
        newCache.setBookname(bookname);
        newCache.setBookpath(bookpath);
        newCache.setBookid(bookid);
        newCache.setPrice(price);
        newCache.setAuthor(author);
        newCache.setYear(year);
        return JSONObject.fromObject(cacheRepository.save(newCache));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public JSONObject addOrderitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid)
    {
        System.out.println("OrderIN\n");
        System.out.println("bookid:" + bookid +"\n");

        BookEntity orderBook = bookRepository.findByBookid(bookid);
        System.out.println("signal1\n");
        int stock = orderBook.getStock();
        int salesVolume = orderBook.getSalesVolume();
        if(stock < number) {
            System.out.println("path1\n");
            System.out.println("Stock: "+stock+" Number: "+number);
            JSONObject jo = new JSONObject();
            jo.put("message","Stock too small");
            orderToPay.clear();
            return jo;//要设置成有一本有问题就取消锁定订单
        }
        BookcacheEntity newCache = new BookcacheEntity();
        newCache.setUserid(userid);
        newCache.setCacheid(cacheid);
        newCache.setNumber(number);
        newCache.setBookname(bookname);
        newCache.setBookpath(bookpath);
        newCache.setBookid(bookid);
        newCache.setPrice(price);
        newCache.setAuthor(author);
        newCache.setYear(year);
        orderToPay.add(newCache);
        String msg = "{\"" +bookname+"\":\"added\"}";
        return JSONObject.fromObject(msg);
    }

    @Modifying
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public List<BookcacheEntity> lockOrder(String time){
        for(BookcacheEntity cache : orderToPay){
            BookEntity orderBook = bookRepository.findByBookid(cache.getBookid());
            int stock = orderBook.getStock();
            int number = cache.getNumber();
            orderBook.setStock(stock - number);
            cacheRepository.updateStatusById(cache.getCacheid(), 1);
            cacheRepository.updateOrdertimeById(cache.getCacheid(), time);
        }
        try {
            kafkaTemplate.send("order", "Status", " Locked");
        } catch (Exception e) {
            System.out.println("发送kafka失败");
        }
        return orderToPay;
    }

    public List<BookcacheEntity> getOrder(){
        return orderToPay;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    @Modifying
    public String payOrder(String time, String price, int userid){
        System.out.println("payOrder IN");
        System.out.println("price:"+price);
        System.out.println("userid:"+userid);
        System.out.println(decoder(price, userid));
        String priceStr = decoder(price, userid);
        Float truePrice = Float.parseFloat(priceStr);
        if(truePrice <= 0){
            return "{\"OrderStatus\" : \"Failed\"}";
        }
        for(BookcacheEntity cache : orderToPay){
            BookEntity orderBook = bookRepository.findByBookid(cache.getBookid());
            int salesVolume = orderBook.getSalesVolume();
            orderBook.setSalesVolume(salesVolume + cache.getNumber());
            cacheRepository.updateStatusById(cache.getCacheid(), 2);
            cacheRepository.updatePaytimeById(cache.getCacheid(), time);
        }
        try {
            kafkaTemplate.send("order", "Status", " Paid");
        } catch (Exception e) {
            System.out.println("发送kafka失败");
        }
        orderToPay.clear();
        return "{\"OrderStatus\" : \"Paid\"}";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid)
    {
        BookcacheEntity newCache = new BookcacheEntity();
        newCache.setUserid(userid);
        newCache.setCacheid(cacheid);
        newCache.setStatus(0);
        newCache.setNumber(number);
        newCache.setBookname(bookname);
        newCache.setBookpath(bookpath);
        newCache.setBookid(bookid);
        newCache.setPrice(price);
        newCache.setAuthor(author);
        newCache.setYear(year);
        cacheRepository.delete(newCache);
    }

}