package bookstorebackend.Controller;

import bookstorebackend.Entity.BookEntity;
import bookstorebackend.Entity.NeoEntity;
import bookstorebackend.Repository.NeoRepository;
import bookstorebackend.Service.BookService;
import bookstorebackend.Service.RedisService;
import bookstorebackend.Service.RmiService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class BookController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private BookService bookService;

    @Autowired
    private NeoRepository neoRepository;

    @GetMapping(value="/books")
    @ResponseBody
    public JSONArray findAll(){
        List<BookEntity> books = bookService.findAll();
        return JSONArray.fromObject(books);
    }

    @GetMapping(value="/bookid/{bookid}")
    @ResponseBody
    public JSONArray findByID(@PathVariable Integer bookid){

        BookEntity book = bookService.findByBookid(bookid);
        return JSONArray.fromObject(book);
    }

    /*@GetMapping(value="/bookid/{bookid}")//RESTful Change（wait to test）
    @ResponseBody
    public HttpServletResponse findByID(@PathVariable Integer bookid, HttpServletResponse response){
        BookEntity book = bookService.findByBookid(bookid);
        JSONArray responseJSONObject = JSONArray.fromObject(book);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
            response.setStatus(200);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(301);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return response;
    }*/

    @PostMapping(value="/books/delete")
    @ResponseBody
    public int deleteBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year, @RequestParam("bookpath")String bookpath, @RequestParam("bookid")Integer bookid, @RequestParam("isbn")String isbn, @RequestParam("book_abstract")String book_abstract, @RequestParam("author_abstract")String author_abstract)
    {
         bookService.deleteBook(bookpath, bookname, price, author, year, bookid, book_abstract, author_abstract, isbn);
         return bookid;
    }

/*    @PostMapping(value="/books/{id}")
    @ResponseBody
    public HttpServletResponse deleteBook(@PathVariable Integer id, HttpServletResponse response)
    {
        response.setStatus(204);
        bookService.deleteBookByBookid(id);
        return response;
    }*/


    @PostMapping(value="/books/save")
    @ResponseBody
    public JSONObject saveBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year, @RequestParam("isbn")String isbn, @RequestParam("book_abstract")String book_abstract, @RequestParam("author_abstract")String author_abstract)
    {
        String bookPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525317727543&di=d877bc51b100ab2e9c7d126131bb2301&imgtype=0&src=http%3A%2F%2Fmedia.leialingerie.com%2FLeia%2F1.1%2Fpa%2F309%2F432%2FFFFFFF%2Fbrands%2Ffreyalingerie%2Faw2011%2Fcrystal-teal%2FMoulded-Bra-Crystal-Freya-Blue.jpg";
        int bookid = 0;
        return JSONObject.fromObject(bookService.addBook(bookPath, bookname, price, author, year, bookid, book_abstract, author_abstract, isbn));
    }



    //ALL BELOW IS TEST FUNCTIONS
    @GetMapping("/rmiTest")
    public String rmiTest(){
        try {
            RmiService service = applicationContext.getBean(RmiService.class);
            return service.rmiDemoService("test");
        }
        catch(Exception e){
            e.printStackTrace();
            return "ERROR";
        }
    }

    @PostMapping(value="/sendKafka")
    @ResponseBody
    public String sendKafka(@RequestParam("msg")String msg, HttpServletRequest request, HttpServletResponse response) {
        try {
            kafkaTemplate.send("test", "key", msg);
            return "发送kafka成功";
        } catch (Exception e) {
            return "发送kafka失败";
        }
    }

    @GetMapping(value = "/saveRedis")
    public void setString() {
        redisService.set("redis_string_test", "springboot redis test");
    }

    @RequestMapping(value = "/getRedis", method = RequestMethod.GET)
    public String getString() {
        String result = redisService.get("redis_string_test");
        System.out.println(result);
        return result;
    }

    @PostMapping ("/neoTest")
    public String neoTest(@RequestParam("msg")String m,@RequestParam("msg")String msg){
        NeoEntity node1 = new NeoEntity("testuser1","8");
        NeoEntity node2 = new NeoEntity("testuser2","9");
        node1.addFriends(node2);
        node2.addFriends(node1);
        neoRepository.save(node1);
        neoRepository.save(node2);
        return "Node saved";
    }


}