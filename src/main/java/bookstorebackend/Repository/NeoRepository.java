package bookstorebackend.Repository;

import bookstorebackend.Entity.NeoEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeoRepository extends Neo4jRepository<NeoEntity, Long> {

    @Override
    Iterable<NeoEntity> findAll();

    @Query("match(n) return n")
    List<NeoEntity> findAllNode();

    @Query("CREATE (user:User {name: {name}, userId:{userId}}) return user")
    NeoEntity saveNode(@Param("name") String name, @Param("userId") String userId);

    @Query("MATCH (p1{userId :{userId1}}),(p2{userId :{userId2}}) \n" + "CREATE (p1)-[r:FRIENDS_WITH ]->(p2)")
    NeoEntity saveEdge(@Param("userId1") String userId1, @Param("userId2") String userId2);

}