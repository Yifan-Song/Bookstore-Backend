package bookstorebackend.Controller;

import bookstorebackend.Service.ChatService;
import net.sf.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/broadcast")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/topic/getResponse")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public String say(String message) throws Exception {
        Thread.sleep(1000);
        return message;
    }

    @RequestMapping("/Welcome1")
    @ResponseBody
    public String say2()throws Exception
    {
        chatService.sendMessage();
        return "is ok";
    }

    @MessageMapping("/chat")
    //在springmvc 中可以直接获得principal,principal 中包含当前用户的信息
    public void handleChat(Principal principal, String message) {
        JSONObject msg = JSONObject.fromObject(message);
        System.out.println(msg);
        String to_name = msg.getString("to_name");
        String from_name = msg.getString("from_name");

        //通过convertAndSendToUser 向用户发送信息,
        // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
        messagingTemplate.convertAndSendToUser(to_name,
                "/queue/notifications", message);
        messagingTemplate.convertAndSendToUser(from_name,
                "/queue/notifications", message);
        /**
         * 操作相等于
         * messagingTemplate.convertAndSend("/user/abel/queue/notifications",principal.getName() + "-send:"
         + message.getName());
         */
    }
}
