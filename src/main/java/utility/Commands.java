package utility;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class Commands {
    private String trigger;

    public static void wolframCommand(String trigger, TextChannel channel, String wolframApiKey) {

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

    public static void helpCommand(TextChannel channel) {
        new MessageBuilder().setEmbed(
                new EmbedBuilder()
                        .setTitle("Girthicel Commands")
                        .addField("!wolfram <query>", "Use this command to search curiosities or solve math stuff")
                        .addField("!clean10", "Clean only the last 10 messages")).send(channel);

    }

}
