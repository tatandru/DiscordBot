import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.retriever.EntityRetrievalStrategy;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.discordjson.json.*;
import discord4j.discordjson.json.gateway.MessageCreate;
import discord4j.discordjson.possible.Possible;
import discord4j.gateway.ShardInfo;
import discord4j.rest.entity.RestChannel;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class Main {

    private final static String TOKEN = "ODE3NDYzNDA2MzA2MjYzMDcy.YEJ4Hw.9O6cBFliL-lDid5FiyPDoUVkc04";

    public static void main(String[] args) {

        GatewayDiscordClient client = DiscordClientBuilder.create(TOKEN)
                .build()
                .login()
                .block();
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    final User self = event.getSelf();
                    System.out.println(String.format(
                            "Logged in as %s#%s", self.getUsername(), self.getDiscriminator()
                    ));
                });
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getContent().equals("adevarat"))
                .flatMap(Message::getChannel)
                .flatMap(messageChannel -> messageChannel.createMessage("adv"))
                .subscribe();
        client.onDisconnect().block();

    }

}
