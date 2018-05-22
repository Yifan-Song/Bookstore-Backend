package BookStoreBackEnd.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BookStoreBackEnd.Entity.UserEntity;
import BookStoreBackEnd.Service.UserService;

@RestController
@RequestMapping(value="/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/users/register")
    @ResponseBody
    public JSONObject addUser(@RequestParam("username")String username, @RequestParam("email")String email, @RequestParam("phone")int phone, @RequestParam("password")String password, @RequestParam("gender")String gender, @RequestParam("address")String address)
    {
        return JSONObject.fromObject(userService.addUser(username, phone, email, gender, address, password));
    }

    @PostMapping(value="/users/get")
    @ResponseBody
    public JSONObject getUser(@RequestParam("userid")int userid)
    {
        return JSONObject.fromObject(userService.findByUserid(userid));
    }
}