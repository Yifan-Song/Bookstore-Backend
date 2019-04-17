package bookstorebackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    //使用SimpMessagingTemplate 向浏览器发送消息
    private SimpMessagingTemplate template;

    public void sendMessage() throws Exception{
        for(int i=0;i<10;i++)
        {
            Thread.sleep(1000);
            String response = "Welcome,yangyibo !"+ i;
            template.convertAndSend("/topic/getResponse",response);
            System.out.println("----------------------yangyibo"+i);
        }
    }
}
