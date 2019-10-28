package spring_boot.spring_boot.MongoDB.MongoDB_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class,args);
    }
}
