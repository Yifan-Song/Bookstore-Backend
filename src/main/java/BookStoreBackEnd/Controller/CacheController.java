package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BookStoreBackEnd.Entity.BookcacheEntity;
import BookStoreBackEnd.Service.CacheService;
import BookStoreBackEnd.Repository.BookRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/api")
public class CacheController {
    @Autowired
    private CacheService cacheService;
    private BookRepository bookRepository;

    @PostMapping(value="/cart/get")
    @ResponseBody
    public JSONArray getCart(@RequestParam("userid")Integer userid){
        List<BookcacheEntity> cart = cacheService.GetCartByUserid(userid);
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

    @PostMapping(value="/historyorders/get")
    @ResponseBody
    public JSONArray getHistoryOrders(@RequestParam("userid")Integer userid){
        List<BookcacheEntity> orders = cacheService.GetHistoryOrdersByUserid(userid);
        return JSONArray.fromObject(orders);
    }

    @PostMapping(value="/orders/add")
    @ResponseBody
    @Scope("prototype")
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JSONObject addOrderItem(@RequestParam("userid") Integer userid, @RequestParam("bookpath")String bookpath, @RequestParam("bookname")String bookname, @RequestParam("price")BigDecimal price, @RequestParam("author")String author, @RequestParam("year")Integer year, @RequestParam("bookid")Integer bookid, @RequestParam("number")Integer number, @RequestParam("cacheid")Integer cacheid){
        System.out.println("add orderitem\n");
        return JSONObject.fromObject(cacheService.addOrderitem(bookpath, bookname, price, author, year, bookid, userid, number, cacheid));
    }

    @PostMapping(value="/orders/lock")
    @ResponseBody
    @Scope("prototype")
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JSONArray getOrderItem(@RequestParam("time")String time){
        System.out.println("lock order\n");
        List<BookcacheEntity> orders = cacheService.lockOrder(time);
        return JSONArray.fromObject(orders);
    }

    @GetMapping(value="/orders/get")
    @ResponseBody
    @Scope("prototype")
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JSONArray getOrderItem(){
        System.out.println("get orderitem\n");
        List<BookcacheEntity> orders = cacheService.getOrder();
        return JSONArray.fromObject(orders);
    }

    @PostMapping(value="/orders/pay")
    @ResponseBody
    @Scope("prototype")
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JSONObject payOrder(@RequestParam("price")String price, @RequestParam("time")String time, HttpServletRequest request){
        HttpSession session=request.getSession();//这就是session的创建
        int userid = (int)session.getAttribute("userid");
        System.out.println("pay order\n");
        return JSONObject.fromObject(cacheService.payOrder(time, price, userid));
    }

}