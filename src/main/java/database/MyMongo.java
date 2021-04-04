package database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MyMongo {
    static final String dbURI = System.getenv("dbURI");
    public Curse curse;
    public MongoCollection<Curse> cursesCollection;
    public MongoDatabase database;

    public MyMongo(){
        this.database=getDB();
        this.cursesCollection=database.getCollection("curses", Curse.class);
        this.curse=cursesCollection.find().first();
    }

    private MongoDatabase getDB() {
        ConnectionString connectionString = new ConnectionString(dbURI);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        return mongoClient.getDatabase("bot");
    }


}
