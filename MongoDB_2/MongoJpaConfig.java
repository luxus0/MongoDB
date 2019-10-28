package spring_boot.spring_boot.MongoDB.MongoDB_2;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories
@ComponentScan
@PropertySource("classpath:application.properties")
public class MongoJpaConfig extends AbstractMongoConfiguration {

    @Value("${com.frugalis.mongo.database}")
    private String database;
    @Value("${com.frugalis.mongo.server}")
    private String host;
    @Value("${com.frugalis.mongo.port}")
    private String port;
    @Value("${com.frugalis.mongo.username}")
    private String username;
    @Value("${com.frugalis.mongo.password}")
    private String password;


    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.frugalis.entity.mongo";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Override
    @Bean
    public MongoClient mongoClient() {

        List<MongoCredential> allCred = new ArrayList<MongoCredential>();
        System.out.println("???????????????????"+username+" "+database+" "+password+" "+host+" "+port);
        allCred.add(MongoCredential.createCredential(username, database, password.toCharArray()));
        MongoClient client = new MongoClient((new ServerAddress(host, Integer.parseInt(port))), allCred);
        client.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        return client;
    }}
