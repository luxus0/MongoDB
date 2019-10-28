package spring_boot.spring_boot.MongoDB.MongoDB_1;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
public class CreditCardApi {


    private CreditCardRepo repo;
    private MongoOperations mongoOperations;
    private MongoTemplate mongoTemplate;
    private BulkOperations bulkOperations;

    @Autowired
    public CreditCardApi(CreditCardRepo repo,MongoOperations mongoOperations, MongoTemplate mongoTemplate,BulkOperations bulkOperations) {
        this.repo = repo;
        this.mongoOperations = mongoOperations;
        this.mongoTemplate = mongoTemplate;
        this.bulkOperations = bulkOperations;
    }

    @GetMapping("/getAll")
    public List<CreditCard> getAllAgeAndName()
    {
        return mongoOperations.find(query(where("age").lt(30).and("name").is("Lukasz")), CreditCard.class);
    }

    @GetMapping("/getCreditCard/{id}")
    public CreditCard getCreditCardById(@PathVariable("id") Object id)
    {
        return mongoOperations.findById(id,CreditCard.class);
    }

    @GetMapping("/getName")
    public CreditCard getOneName()
    {
        return mongoOperations.findOne(query(where("name").is("Franek")),CreditCard.class);
    }



    @GetMapping("/getNotEqual")
    public List<CreditCard> getNotEqual()
    {
        return mongoOperations.find(query(where("age").ne(30)),CreditCard.class);
    }

    @GetMapping("/getList")
    public List<CreditCard> getList() {
        return mongoOperations.find(query(where("numberCount").in(453546774)), CreditCard.class);

    }

    @GetMapping("/getRegex")
    public List<CreditCard> getRegex()
    {
        return mongoOperations.find(query(where("age").regex("abc.*")), CreditCard.class);
    }

    @PostMapping("/createCollection")
    public MongoCollection<Document> createCollection()
    {
        return mongoOperations.createCollection(CreditCard.class, CollectionOptions.just(Collation.of(Locale.CANADA)));
    }

    @PostMapping("/insertCreditCard")
    public void insertCreditCard() {
        CreditCard card = new CreditCard("1", "Marek", "Bozyls", "VERTOS", 654564656, 23, 654654666);
        CreditCard card2 = new CreditCard("2", "Wladimir", "Gonzos", "DAKOS", 654564656, 28, 654654666);
        CreditCard card3 = new CreditCard("3", "Jeremy", "Barenko", "NERTOM", 654564656, 21, 654654666);
        CreditCard card4 = new CreditCard("4", "Blatis", "Jowitz", "VAREN", 654566546, 26, 654654666);
        CreditCard card5 = new CreditCard("5", "Grurezk", "Eratel", "VERTOS", 6547777, 20, 654654666);

        if (mongoOperations.collectionExists(CreditCard.class)) {
            mongoOperations.insertAll(Arrays.asList(card));
            mongoOperations.insertAll(Arrays.asList(card2));
            mongoOperations.insertAll(Arrays.asList(card3));
            mongoOperations.insertAll(Arrays.asList(card4));
            mongoOperations.insertAll(Arrays.asList(card5));
        }

    }

    @PostMapping("/insertCollection")
    public void insertCollection()
    {
        Map<String,String> map = new HashMap<>();
        map.put("id","3");
        map.put("name","garenz");
        map.put("surname","Wlacko");
        mongoOperations.insert(map);


    }
        @PutMapping("/updateCreditCard")
        public void updateCreditCard()
        {
            CreditCard card = new CreditCard("5", "Grurezk", "Eratel", "VERTOS", 6547777, 20, 654654666);
            CreditCard card3 = new CreditCard("3", "Jeremy", "Barenko", "NERTOM", 654564656, 21, 654654666);

            mongoOperations.updateFirst(query(where("id").is("5").and("name").is("Grurezk")), update("pesel",123455689),CreditCard.class);
            mongoOperations.updateFirst(query(where("name").is("Jeremy").and("nameCard").lt(99999999)),update("id","2"),CreditCard.class);
        }

        @PutMapping("/findAndUpdate")
        public void updateCreditcard()
        {
              String creditCard = mongoOperations.findAndReplace(query(where("id").is("2")),"4");
                System.out.println(creditCard);
        }

        @DeleteMapping("/findAndRemoveCreditcard")
        public void findAndRemoveCreditCard()
        {
            CreditCard card3 = new CreditCard("3", "Jeremy", "Barenko", "NERTOM", 654564656, 21, 654654666);
            CreditCard card4 = new CreditCard("4", "Blatis", "Jowitz", "VAREN", 654566546, 26, 654654666);
            CreditCard card5 = new CreditCard("5", "Grurezk", "Eratel", "VERTOS", 6547777, 20, 654654666);

            List<CreditCard> findAndRemove = mongoOperations.findAllAndRemove(query(where("id").is("3").and("4").and("5")),CreditCard.class);

            if(mongoOperations.collectionExists(CreditCard.class))
            {
                System.out.println("Collection is exist");
            }
            else
            {
                System.out.println("Delete collection");
            }


        }

    @DeleteMapping("/deleteAll")
    public void deleteAll()
    {
        mongoOperations.remove(CreditCard.class);
    }


//BULK_OPERATIONS

    @PostMapping("/insertBulk")
    public void insertBulk()
    {
        List<CreditCard> creditCardList = new ArrayList<>();

        creditCardList.add(new CreditCard("3", "Jeremy", "Barenko", "NERTOM", 654564656, 21, 654654666));
        creditCardList.add(new CreditCard("4", "Blatis", "Jowitz", "VAREN", 654566546, 26, 654654666));
        creditCardList.add(new CreditCard("5", "Grurezk", "Eratel", "VERTOS", 6547777, 20, 654654666));


        bulkOperations.insert(creditCardList);
    }







}
