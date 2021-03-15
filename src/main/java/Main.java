import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
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
        api.updateActivity("Use !girthicel for commands");

        api.addMessageCreateListener(event -> {
            TextChannel channel = event.getChannel();
            String trigger = event.getMessageContent();
            if (trigger.startsWith("!wolfram")) {
                if (trigger.length() > 8) {
                    try {
                        File imageResponse = WolframAlphaSimpleApi.apiRequest(wolframApiKey, trigger.substring(8));
                        channel.sendMessage(imageResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    channel.sendMessage(new EmbedBuilder().setDescription("Try like this-> !wolfram <query>"));
                }
            }
            if (trigger.startsWith("!girthicel")) {
                new MessageBuilder().setEmbed(
                        new EmbedBuilder()
                                .setTitle("Girthicel Commands")
                                .addField("!wolfram <query>", "Use this command to search curiosities or solve math stuff")
                                .addField("!clean10", "Clean only the last 10 messages")).send(channel);
            }
            if(trigger.startsWith("!clean10")){
                event.getChannel().getMessageCache().setCapacity(10);
                api.getCachedMessages().deleteAll();
                event.getChannel().sendMessage("Executed !clean10");
            }
        });


    }

}
