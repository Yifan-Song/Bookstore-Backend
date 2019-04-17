package bookstorebackend.Service;

import net.sf.json.JSONObject;
import bookstorebackend.Entity.IconEntity;

public interface IconService {

    public IconEntity findByUserid(int id);

    public JSONObject setIcon(int id, String iconString);
}