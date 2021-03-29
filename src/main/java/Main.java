import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.Curse;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import utility.Commands;


import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class Main {


    static String TOKEN = System.getenv("TOKEN");
    static final String wolframApiKey = System.getenv("wolframApiKey");
    static final String dbURI = System.getenv("dbURI");
    static MongoDatabase db;


    public static void main(String[] args) {
        initDB();
        MongoCollection<Curse> cursesCollection = db.getCollection("curses", Curse.class);
        Curse curse = cursesCollection.find().first();


        //Daca am mai multe documente implementarea de jos

//        List<Curse> curs = new ArrayList<>();
//        curses.find().forEach(curse -> curs.add(curse));


        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();
        api.updateActivity("Use !girthicel for commands");

        api.addMessageCreateListener(event -> {
            TextChannel channel = event.getChannel();

            String trigger = event.getMessageContent();
            if (trigger.startsWith("!wolfram ")) {
                Commands.wolframCommand(trigger, channel, wolframApiKey);
            }

            if (trigger.startsWith("!girthicel")) {
                Commands.helpCommand(channel);
            }

            if (trigger.startsWith("!injura ")) {
                Commands.curseCommand(trigger, channel, curse, event);
            }

        });


    }

    private static void initDB() {
        ConnectionString connectionString = new ConnectionString(dbURI);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        db = mongoClient.getDatabase("bot");
    }

}
