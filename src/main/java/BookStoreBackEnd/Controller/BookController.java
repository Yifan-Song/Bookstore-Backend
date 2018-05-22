package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Service.BookService;


@RestController
@RequestMapping(value="/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value="/books")
    @ResponseBody
    public JSONArray findAll(){
        List<BookEntity> books = bookService.findAll();
        System.out.println(JSONArray.fromObject(books));
        return JSONArray.fromObject(books);
    }

    @PostMapping(value="/books/delete")
    @ResponseBody
    public int deleteBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year, @RequestParam("bookpath")String bookpath, @RequestParam("bookid")Integer bookid)
    {
         bookService.deleteBook(bookpath, bookname, price, author, year, bookid);
         return bookid;
    }

    @PostMapping(value="/books/save")
    @ResponseBody
    public JSONObject saveBook(@RequestParam("bookname")String bookname, @RequestParam("author")String author, @RequestParam("price")BigDecimal price, @RequestParam("year")Integer year)
    {
        String bookPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525317727543&di=d877bc51b100ab2e9c7d126131bb2301&imgtype=0&src=http%3A%2F%2Fmedia.leialingerie.com%2FLeia%2F1.1%2Fpa%2F309%2F432%2FFFFFFF%2Fbrands%2Ffreyalingerie%2Faw2011%2Fcrystal-teal%2FMoulded-Bra-Crystal-Freya-Blue.jpg";
        int bookid = 0;
        return JSONObject.fromObject(bookService.addBook(bookPath, bookname, price, author, year, bookid));
    }
}