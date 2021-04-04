import database.MyMongo;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import utility.ChatListener;


public class Main {


    static final String TOKEN = System.getenv("TOKEN");


    public static void main(String[] args) {
        MyMongo mongo = new MyMongo();

        //Daca am mai multe documente implementarea de jos

//        List<Curse> curs = new ArrayList<>();
//        curses.find().forEach(curse -> curs.add(curse));


        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();
        ChatListener.discordCommands(api, mongo);


    }


}
