package BookStoreBackEnd.Service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.BookcacheEntity;
import BookStoreBackEnd.Repository.CacheRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CacheService {
    @Autowired
    private CacheRepository cacheRepository;

    public List<BookcacheEntity> GetCartByUserid(int userid)
    {
        return cacheRepository.findByUseridAndStatus(userid, 0);
    }

    public List<BookcacheEntity> GetOrdersByUserid(int userid)
    {
        return cacheRepository.findByUseridAndStatus(userid, 1);
    }

    public JSONObject addCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number)
    {
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
        if(cacheRepository.findByBookidAndStatus(bookid, 0) != null) {
            int cacheid = cacheRepository.findByBookidAndStatus(bookid, 0).getCacheid();
            int oldnum = cacheRepository.findByBookidAndStatus(bookid, 0).getNumber();
            newCache.setCacheid(cacheid);
            newCache.setNumber(oldnum + 1);
            return JSONObject.fromObject(cacheRepository.save(newCache));
        }
        return JSONObject.fromObject(cacheRepository.save(newCache));
    }

    public JSONObject updateCartitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid)
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
        return JSONObject.fromObject(cacheRepository.save(newCache));
    }

    public JSONObject addOrderitem(String bookpath, String bookname, BigDecimal price, String author, int year, int bookid, int userid, int number, int cacheid)
    {
        BookcacheEntity newCache = new BookcacheEntity();
        newCache.setUserid(userid);
        newCache.setCacheid(cacheid);
        newCache.setStatus(1);
        newCache.setNumber(number);
        newCache.setBookname(bookname);
        newCache.setBookpath(bookpath);
        newCache.setBookid(bookid);
        newCache.setPrice(price);
        newCache.setAuthor(author);
        newCache.setYear(year);
        return JSONObject.fromObject(cacheRepository.save(newCache));
    }

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