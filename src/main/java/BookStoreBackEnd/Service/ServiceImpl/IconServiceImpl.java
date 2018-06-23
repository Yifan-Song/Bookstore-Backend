package BookStoreBackEnd.Service.ServiceImpl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.IconEntity;
import BookStoreBackEnd.Repository.IconRepository;
import BookStoreBackEnd.Service.IconService;

import java.util.List;

@Service
public class IconServiceImpl implements IconService {
    @Autowired
    private IconRepository iconRepository;

    public IconEntity findByUserid(int id){
        System.out.println("Getting Icon 2");
        return iconRepository.findByUserid(id);
    }

    public JSONObject setIcon(int id, String iconString)
    {
        System.out.println("Setting Icon 2\n");
        IconEntity icon = iconRepository.findByUserid(id);
        if (icon == null){
            System.out.println("No User");
            icon = new IconEntity();
            icon.setUserid(id);
        }
        icon.setIcon(iconString);


        System.out.println("userid: " + id);
        System.out.println("iconString: " + iconString);
        System.out.println("Icon: ");
        System.out.println(icon.getUserid());
        System.out.println(icon.getIcon());

        return JSONObject.fromObject(iconRepository.save(icon));
    }
}
