package utility;

import database.MyMongo;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import utility.pojo.SearchedShow;
import utility.pojo.Shows;

public class ChatListener {
    static final String wolframApiKey = System.getenv("wolframApiKey");
    static final String omdbApiKey=System.getenv("omdb");

    public static void discordCommands(DiscordApi api, MyMongo mongo) {
        api.updateActivity("!girthicel for commands");

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
                Commands.curseCommand(trigger, channel, mongo.curse, event);
            }
            if(trigger.startsWith("!movie")){
                Commands.movieCommand(trigger,channel,omdbApiKey);
            }

        });
    }
}
