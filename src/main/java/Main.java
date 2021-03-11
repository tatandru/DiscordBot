import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.util.cache.MessageCache;
import utility.WolframAlphaSimpleApi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/token.txt");
        Scanner scanner = new Scanner(file);
        final String TOKEN = scanner.nextLine();
        final String wolframApiKey = scanner.nextLine();
        MessageBuilder message = new MessageBuilder().setContent("!clean");


        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equals("!clean10")) {
                ;
                event.getChannel().getMessageCache().setCapacity(10);
                api.getCachedMessages().deleteAll();
                event.getChannel().sendMessage("Executed !clean10");
            }
        });
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equals("!romania")) {
                try {

                    File imageResponse= WolframAlphaSimpleApi.apiRequest(wolframApiKey,"usain bolt");
                    event.getChannel().sendMessage(imageResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
