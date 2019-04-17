package bookstorebackend.Controller;

import bookstorebackend.Service.IconService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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