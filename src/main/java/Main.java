import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.cache.MessageCache;
import utility.Commands;
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

        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();
        api.updateActivity("Use !girthicel for commands");
        api.setMessageCacheSize(10,43200);

        api.addMessageCreateListener(event -> {
            TextChannel channel = event.getChannel();
            String trigger = event.getMessageContent();
            if (trigger.startsWith("!wolfram")) {
                Commands.wolframCommand(trigger,channel,wolframApiKey);
            }
            if (trigger.startsWith("!girthicel")) {
                Commands.helpCommand(channel);
            }

        });


    }

}
