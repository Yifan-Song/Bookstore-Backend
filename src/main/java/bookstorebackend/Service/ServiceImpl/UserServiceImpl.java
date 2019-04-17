package bookstorebackend.Service.ServiceImpl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookstorebackend.Entity.UserEntity;
import bookstorebackend.Repository.UserRepository;
import bookstorebackend.Service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository UserRepository;

    public List<UserEntity> findAll(){
        return UserRepository.findAll();
    }

    public UserEntity findByUserid(int id){
        return UserRepository.findByUserid(id);
    }

    public UserEntity findByUsername(String name){
        return UserRepository.findByUsername(name);
    }

    public JSONObject addUser(String username, String phone, String email, String gender, String address, String password)
    {
        System.out.println(username);
        UserEntity newUser = new UserEntity();
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setGender(gender);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setRole("USER");
        newUser.setUserid(0);
        newUser.setUsername(username);
        System.out.println(newUser.toString());
        return JSONObject.fromObject(UserRepository.save(newUser));
    }
}