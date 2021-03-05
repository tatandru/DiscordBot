import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

import java.io.File;
import java.io.FileInputStream;


public class Main {

    private final static String TOKEN = "ODE3NDYzNDA2MzA2MjYzMDcy.YEJ4Hw.9O6cBFliL-lDid5FiyPDoUVkc04";

    public static void main(String[] args) {

        GatewayDiscordClient client = DiscordClientBuilder.create(TOKEN)
                .build()
                .login()
                .block();
    }

}
