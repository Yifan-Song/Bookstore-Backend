
package bookstorebackend.Controller;

import bookstorebackend.Service.BookDetailService;
import bookstorebackend.Service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api")
public class BookDetailController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private BookDetailService bookDetailService;

    @GetMapping(value="/bookimages/{bookid}")
    @ResponseBody
    public String findImageByBookid(@PathVariable Integer bookid){
        String bookImagePath = redisService.get("bookImage"+bookid);
        System.out.println(bookImagePath);
        if(bookImagePath == null){
            String imagePath = bookDetailService.findImageByBookid(bookid);
            redisService.set("bookImage"+bookid, imagePath);
            logger.debug(imagePath);
            return "FromDB:"+imagePath;
        }
        else{
            logger.debug(bookImagePath);
            return "FromRedis:"+bookImagePath;
        }
    }

}