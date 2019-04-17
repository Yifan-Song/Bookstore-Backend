package bookstorebackend.Service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import bookstorebackend.Entity.UserEntity;

import java.util.List;

@Service
public interface UserService {

    public List<UserEntity> findAll();

    public UserEntity findByUserid(int id);

    public UserEntity findByUsername(String name);

    public JSONObject addUser(String username, String phone, String email, String gender, String address, String password);
}