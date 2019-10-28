package spring_boot.spring_boot.MongoDB.MongoDB_1;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDatabaseUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.MongoDbFactoryParser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@Configuration
@PropertySource("classpath:MongoDB.properties")
public class Properties extends AbstractMongoConfiguration{



    @Value("${mongo.name}")
    private String dbName;

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;


    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;


    @Override
    public MongoClient mongoClient() {
        return new MongoClient(this.host,this.port);
    }

    @Override
    protected String getDatabaseName() {
        return this.dbName;
    }


    public MongoTemplate getMongoTemplate() throws Exception {
        UserCredentials credentials = new UserCredentials(this.username,this.password);

        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(),getDatabaseName());

        return mongoTemplate;

    }





}
