package BookStoreBackEnd.Service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BookStoreBackEnd.Entity.IconEntity;
import BookStoreBackEnd.Repository.IconRepository;

import java.util.List;

public interface IconService {

    public IconEntity findByUserid(int id);

    public JSONObject setIcon(int id, String iconString);
}