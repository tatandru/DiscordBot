package utility;

import database.Curse;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Commands {

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
                        .addField("!injura <@user>","Use this command to curse other people")
                        .addField("!wolfram <query>", "Use this command to search curiosities or solve math stuff")).send(channel);

    }

    public static void curseCommand(String trigger, TextChannel channel, Curse curse, MessageCreateEvent event){
        Random random = new Random();
        Message message = event.getMessage();
        if( message.getMentionedUsers().size()>0){

            if (trigger.length() > 9) {

                List<User> users = message.getMentionedUsers();
                String plebs = "";
                for (int i = 0; i < users.size(); i++) {
                    plebs = plebs + users.get(i).getMentionTag() + " ";
                }
                channel.sendMessage(curse.getCurses().get(random.nextInt(curse.getCurses().size())) + " " + plebs);

            }
        }else{
            channel.sendMessage(new EmbedBuilder().setDescription("Try like this-> !injura <@user>"));
        }
    }

}
