import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.util.cache.MessageCache;

import java.io.*;
import java.util.Scanner;



public class Main {



    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/token.txt");
        Scanner scanner =new Scanner(file);
        final String TOKEN = scanner.nextLine();
        MessageBuilder message= new MessageBuilder().setContent("!clean");

        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();

        api.addMessageCreateListener(event -> {
            if(event.getMessageContent().equals("!clean10")){ ;
                event.getChannel().getMessageCache().setCapacity(10);
               api.getCachedMessages().deleteAll();
               event.getChannel().sendMessage("Executed !clean10");
            }
        });

    }

}
