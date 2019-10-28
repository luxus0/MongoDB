package spring_boot.spring_boot.MongoDB.MongoDB_2;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends MongoRepository<Player,Integer> {
}
