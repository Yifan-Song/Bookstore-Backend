package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BookStoreBackEnd.Entity.IconEntity;
import BookStoreBackEnd.Service.IconService;

@RestController
@RequestMapping(value="/api")
public class IconController {
    @Autowired
    private IconService iconService;

    @PostMapping(value="/users/icon/get")
    @ResponseBody
    public JSONObject getIcon(@RequestParam("userid")int userid)
    {
        System.out.println("Getting Icon");
        return JSONObject.fromObject(iconService.findByUserid(userid));
    }

    @PostMapping(value="/users/icon/set")
    @ResponseBody
    public JSONObject setIcon(@RequestParam("userid")int userid, @RequestParam("iconString")String iconString)
    {
        System.out.println("Setting Icon\n");
        return JSONObject.fromObject(iconService.setIcon(userid,iconString));
    }
}