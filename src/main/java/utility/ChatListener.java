package utility;

import database.MyMongo;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;

public class ChatListener {
    static final String wolframApiKey = System.getenv("wolframApiKey");

    public static void discordCommands(DiscordApi api, MyMongo mongo) {
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
                Commands.curseCommand(trigger, channel, mongo.curse, event);
                System.out.println("from local");
            }

        });
    }
}
