package utility;

import database.Curse;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import utility.pojo.SearchedShow;
import utility.pojo.Shows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;


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
                        .addField("!injura <@user>", "Use this command to curse other people")
                        .addField("!wolfram <query>", "Use this command to search curiosities or solve math stuff")
                        .addField("!movie <title> or !series <title>", "Use this command to search for info on the show")).send(channel);

    }

    public static void curseCommand(String trigger, TextChannel channel, Curse curse, MessageCreateEvent event) {
        Random random = new Random();
        Message message = event.getMessage();
        if (message.getMentionedUsers().size() > 0) {

            if (trigger.length() > 9) {

                List<User> users = message.getMentionedUsers();
                String plebs = "";
                for (int i = 0; i < users.size(); i++) {
                    plebs = plebs + users.get(i).getMentionTag() + " ";
                }
                channel.sendMessage(curse.getCurses().get(random.nextInt(curse.getCurses().size())) + " " + plebs);

            }
        } else {
            channel.sendMessage(new EmbedBuilder().setDescription("Try like this-> !injura <@user>"));
        }
    }

    public static void movieCommand(String trigger, TextChannel channel, String omdbApiKey) {
        EmbedBuilder embed = new EmbedBuilder();
        Shows show;

        if (trigger.length() > 7) {

            show = OpenMovieDatabaseApi.requestByID(trigger.substring(7), omdbApiKey);
            if (show != null) {
                if (show.getResponse().equals("True")) {

                    embed.setTitle("Search results for: " + trigger.substring(7));
                    embed.addField(show.getTitle(), "Released in: " + show.getYear() + " ");
                    embed.addField("Genre", show.getGenre());
                    embed.addField("Plot", show.getPlot());
                    try {
                        for (int i = 0; i < show.getRatingsList().size(); i++) {
                            embed.addInlineField(show.getRatingsList().get(i).getSource(), show.getRatingsList().get(i).getValue());
                        }
                    } catch (NullPointerException nullPointerException) {
                        embed.addField("Rating", "No rating found");
                    }


                    embed.addField("Actors", show.getActors());
                    embed.setImage(show.getImageUrl());
                } else if (show.getResponse().equals("False")) {
                    embed.addField("Show/movie not found", "check the spelling of your title");
                }
            } else {
                embed.addField("Show/movie not found", "check the spelling of your title");
            }


        } else {
            embed.addField("Show/movie not found", "check the spelling of your title");
        }


        channel.sendMessage(embed);


    }

}
