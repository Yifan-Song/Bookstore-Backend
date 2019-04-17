package bookstorebackend.Controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bookstorebackend.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/users/register")
    @ResponseBody
    public JSONObject addUser(@RequestParam("username")String username, @RequestParam("email")String email, @RequestParam("phone")String phone, @RequestParam("password")String password, @RequestParam("gender")String gender, @RequestParam("address")String address)
    {
        System.out.println(username);
        return JSONObject.fromObject(userService.addUser(username, phone, email, gender, address, password));
    }

    @PostMapping(value="/users/get")
    @ResponseBody
    public JSONObject getUser(@RequestParam("userid")int userid, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        System.out.println(session.getAttribute("username"));
        return JSONObject.fromObject(userService.findByUserid(userid));
    }
}