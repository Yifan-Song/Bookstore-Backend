package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Service.BookService;
import BookStoreBackEnd.Service.RmiService;
import org.springframework.context.ApplicationContext;

@RestController
@RequestMapping(value="/api")
public class BookController {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private BookService bookService;

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

    @PostMapping(value="/books/delete")
    @ResponseBody
    public int deleteBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year, @RequestParam("bookpath")String bookpath, @RequestParam("bookid")Integer bookid, @RequestParam("isbn")String isbn, @RequestParam("book_abstract")String book_abstract, @RequestParam("author_abstract")String author_abstract)
    {
         bookService.deleteBook(bookpath, bookname, price, author, year, bookid, book_abstract, author_abstract, isbn);
         return bookid;
    }

    @PostMapping(value="/books/save")
    @ResponseBody
    public JSONObject saveBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year, @RequestParam("isbn")String isbn, @RequestParam("book_abstract")String book_abstract, @RequestParam("author_abstract")String author_abstract)
    {
        String bookPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525317727543&di=d877bc51b100ab2e9c7d126131bb2301&imgtype=0&src=http%3A%2F%2Fmedia.leialingerie.com%2FLeia%2F1.1%2Fpa%2F309%2F432%2FFFFFFF%2Fbrands%2Ffreyalingerie%2Faw2011%2Fcrystal-teal%2FMoulded-Bra-Crystal-Freya-Blue.jpg";
        int bookid = 0;
        return JSONObject.fromObject(bookService.addBook(bookPath, bookname, price, author, year, bookid, book_abstract, author_abstract, isbn));
    }

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

}