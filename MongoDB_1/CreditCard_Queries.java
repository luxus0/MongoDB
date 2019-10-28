package spring_boot.spring_boot.MongoDB.MongoDB_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class CreditCard_Queries {

    @Autowired
    private MongoOperations mongoOperations;
    private CreditCardRepo repo;


    public static void main(String args[])
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Properties.class);
        CreditCard_Queries queries = ctx.getBean(CreditCard_Queries.class);
        queries.MongoTemplateForQueries();
        ctx.close();
    }

    private void MongoTemplateForQueries() {

        //findOne
        CreditCard creditCard = mongoOperations.findOne(new Query(where("name").is("Lukasz")), CreditCard.class);


        //find
        Query nameQuery = query(where("name").is("Andrzej"));
        List<CreditCard> creditCardList = mongoOperations.find(nameQuery, CreditCard.class);

        //less than
        List<CreditCard> CreditCardList = mongoOperations.find(query(where("age").lt(12)), CreditCard.class);

        //not equal
        List<CreditCard> notEqual = mongoOperations.find(query(where("surname").ne("Januszewski")), CreditCard.class);


        //beetwen
        Criteria beetwen = where("age").gt(12).lt(35);
        mongoOperations.find(query(beetwen), CreditCard.class);

        //in List
        List<CreditCard> inList = mongoOperations.find(query(where("pesel").in(247890654, 6734344, 8678678, 23546565, 9995654)), CreditCard.class);


        //regular expression
        List<CreditCard> regularExpression = mongoOperations.find(query(where("name").regex("G[ae].*")), CreditCard.class);

        //not regular expression
        List<CreditCard> notRegularExpression = mongoOperations.find(query(where("surname").not().regex("G[ej].*")), CreditCard.class);

        //subdocument
        List<CreditCard> subdocument = mongoOperations.find(query(where("age").is(34)), CreditCard.class);

        //GreaterThanandLessThan
        List<CreditCard> lassThanGreaterThan = mongoOperations.find(query(where("age").lt(20).and("pesele").gt(100000)), CreditCard.class);

        //distance
        List<CreditCard> distanceMinMax = mongoOperations.find(query(where("age").minDistance(20).andOperator(where("age").maxDistance(30))), CreditCard.class);

        //getAllCreditCard
        List<CreditCard> getAllCreditCard = mongoOperations.findAll(CreditCard.class);



    }

    private void basicQuery()
    {
        BasicQuery basicQuery = new BasicQuery("{id : '1', name : 'Jacek' , surname : 'Berezowski',nameCard : 'VISA ELECTRON', pesel : { $lt : 999999}, age : { $gt : 12},numberCount : { 999999} }");
        mongoOperations.find(basicQuery, CreditCard.class);



        BasicQuery basicQuery2 = new BasicQuery("{id : '2', name : 'Muranka' , surname : 'Girenza',nameCard : 'BROTESTY', pesel : { $gt : 999999}, age : { $lt : 67},numberCount : { 3453455} }");
        mongoOperations.findOne(basicQuery2,CreditCard.class);

        BasicQuery basicQuery3 = new BasicQuery("{id : '3', name : 'Dateria' , surname : 'Bizates',nameCard : 'DURENTAZ', pesel : 456546, age : 123456,numberCount : { 3453455} }");
        mongoOperations.findAllAndRemove(basicQuery3,CreditCard.class);

        BasicQuery basicQuery4 = new BasicQuery("{id : '2', name : 'Muranka' , surname : 'Girenza',nameCard : 'BROTESTY', pesel : { $gt : 999999}, age : { $lt : 67},numberCount : { 3453455} }");
       long count =  mongoOperations.count(basicQuery4,"name");
       System.out.println("Count query: " +count);





    }

    private void addCreditCard()
        {

            if(mongoOperations.collectionExists(CreditCard.class))
            {
                mongoOperations.dropCollection(CreditCard.class);

            }

            CreditCard[] creditCards = new CreditCard[]{
                    new CreditCard("1","Wladmimir", "Strenko", "Reifeizen", 1254546654,20,644456767),
                    new CreditCard("2","Jarek", "Pioiol", "CITERAZ", 1254546654,21,644456767),
                    new CreditCard("3","Jarek", "Pasztol", "WNZB", 12545454,23,644456767),
                    new CreditCard("4","Dario", "Bezka", "Mbank", 1254546654,27,644456767),
                    new CreditCard("5","Inar", "Gteop", "", 1254546654,54,644456767),
                    new CreditCard("6","Bikiol", "Strenko", "Duratz", 12545654,40,644456767),


            };

           // mongoOperations.insert(creditCards);
            mongoOperations.insertAll(Arrays.asList(creditCards));

            //big data ordered
            mongoOperations.bulkOps(BulkOperations.BulkMode.ORDERED,"ORDERED");

        }






    }



