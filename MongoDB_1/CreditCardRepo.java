package spring_boot.spring_boot.MongoDB.MongoDB_1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CreditCardRepo  extends MongoRepository<CreditCard,Object> {

    public List<CreditCard> findCreditCardByName(String name);
}
