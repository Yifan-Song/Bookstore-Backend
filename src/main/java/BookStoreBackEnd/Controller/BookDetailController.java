
package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BookStoreBackEnd.Entity.BookEntity;
import BookStoreBackEnd.Service.BookDetailService;


@RestController
@RequestMapping(value="/api")
public class BookDetailController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookDetailService bookDetailService;

    @GetMapping(value="/bookimages/bookid/{bookid}")
    @ResponseBody
    public String findImageByBookid(@PathVariable Integer bookid){
        String imagePath = bookDetailService.findImageByBookid(bookid);
        logger.debug(imagePath);
        return imagePath;
    }
}

