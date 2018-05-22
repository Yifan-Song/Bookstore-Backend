package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BookStoreBackEnd.Entity.BookcacheEntity;
import BookStoreBackEnd.Service.CacheService;


@RestController
@RequestMapping(value="/api")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @PostMapping(value="/cart/get")
    @ResponseBody
    public JSONArray getCart(@RequestParam("userid")Integer userid){
        List<BookcacheEntity> cart = cacheService.GetCartByUserid(userid);
        System.out.println(JSONArray.fromObject(cart));
        return JSONArray.fromObject(cart);
    }

    @PostMapping(value="/cart/add")
    @ResponseBody
    public JSONObject addCartItem(@RequestParam("userid")Integer userid, @RequestParam("bookpath")String bookpath, @RequestParam("bookname")String bookname, @RequestParam("price")BigDecimal price, @RequestParam("author")String author, @RequestParam("year")Integer year, @RequestParam("bookid")Integer bookid, @RequestParam("number")Integer number){
        return JSONObject.fromObject(cacheService.addCartitem(bookpath, bookname, price, author, year, bookid, userid, number));
    }

    @PostMapping(value="/cart/update")
    @ResponseBody
    public JSONObject updateCartItem(@RequestParam("userid")Integer userid, @RequestParam("bookpath")String bookpath, @RequestParam("bookname")String bookname, @RequestParam("price")BigDecimal price, @RequestParam("author")String author, @RequestParam("year")Integer year, @RequestParam("bookid")Integer bookid, @RequestParam("number")Integer number, @RequestParam("cacheid")Integer cacheid){
        return JSONObject.fromObject(cacheService.updateCartitem(bookpath, bookname, price, author, year, bookid, userid, number, cacheid));
    }

    @PostMapping(value="/cart/delete")
    @ResponseBody
    public void deleteCartItem(@RequestParam("userid")Integer userid, @RequestParam("bookpath")String bookpath, @RequestParam("bookname")String bookname, @RequestParam("price")BigDecimal price, @RequestParam("author")String author, @RequestParam("year")Integer year, @RequestParam("bookid")Integer bookid, @RequestParam("number")Integer number,@RequestParam("cacheid")Integer cacheid ){
        System.out.println("Deleting...");
        cacheService.deleteCartitem(bookpath, bookname, price, author, year, bookid, userid, number, cacheid);
    }

    @PostMapping(value="/orders/get")
    @ResponseBody
    public JSONArray getOrders(@RequestParam("userid")Integer userid){
        List<BookcacheEntity> orders = cacheService.GetOrdersByUserid(userid);
        System.out.println(JSONArray.fromObject(orders));
        return JSONArray.fromObject(orders);
    }

    @PostMapping(value="/orders/add")
    @ResponseBody
    public JSONObject addOrderItem(@RequestParam("userid")Integer userid, @RequestParam("bookpath")String bookpath, @RequestParam("bookname")String bookname, @RequestParam("price")BigDecimal price, @RequestParam("author")String author, @RequestParam("year")Integer year, @RequestParam("bookid")Integer bookid, @RequestParam("number")Integer number, @RequestParam("cacheid")Integer cacheid){
        return JSONObject.fromObject(cacheService.addOrderitem(bookpath, bookname, price, author, year, bookid, userid, number, cacheid));
    }
}
