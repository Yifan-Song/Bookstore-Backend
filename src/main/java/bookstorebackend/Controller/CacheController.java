package bookstorebackend.Controller;

import bookstorebackend.Entity.BookcacheEntity;
import bookstorebackend.Repository.BookRepository;
import bookstorebackend.Service.CacheService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class CacheController {
    @Autowired
    private CacheService cacheService;
    private BookRepository bookRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

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
    public JSONArray lockOrder(@RequestParam("time")String time, HttpServletRequest request){
        HttpSession session=request.getSession();
        int userid = (int)session.getAttribute("userid");
        try {
            System.out.println("Send in");
            kafkaTemplate.send("order", "Time", time);
            kafkaTemplate.send("order", "UserId", ""+userid);
            kafkaTemplate.send("order", "Status", "Locking");
            System.out.println("Send out");
        } catch (Exception e) {
            System.out.println("发送kafka失败");
        }
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
    public JSONObject doPayOrder(@RequestParam("price")String price, @RequestParam("time")String time, HttpServletRequest request){
        System.out.println("DOPAY IN");
        HttpSession session=request.getSession();
        int userid = (int)session.getAttribute("userid");
        System.out.println("userid"+userid);
        System.out.println("pay order\n");
        try {
            kafkaTemplate.send("order", "Time", time);
            kafkaTemplate.send("order", "UserId", ""+userid);
            kafkaTemplate.send("order", "Status", "Paying");
        } catch (Exception e) {
            System.out.println("发送kafka失败");
        }
        return JSONObject.fromObject(cacheService.payOrder(time, price, userid));
    }

}