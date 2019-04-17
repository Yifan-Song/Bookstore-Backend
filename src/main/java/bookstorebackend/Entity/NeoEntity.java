package bookstorebackend.Entity;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "User")
public class NeoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private  String name;

    @Property
    private String userId;

    @Relationship(type = "FRIENDS", direction = Relationship.UNDIRECTED)
    public Set<NeoEntity> friends;

    private NeoEntity() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public NeoEntity(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void addFriends(NeoEntity node) {
        if (friends == null) {
            friends = new HashSet<>();
        }
        friends.add(node);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
    public boolean equals(Object other) {

        if (this == other)
            return true;

        if (id == null)
            return false;

        if (! (other instanceof NeoEntity))
            return false;

        return id.equals(((NeoEntity) other).id);
    }

    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

}
