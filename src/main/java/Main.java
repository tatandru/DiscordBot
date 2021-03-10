import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.*;
import java.util.Scanner;



public class Main {



    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/token.txt");
        Scanner scanner =new Scanner(file);
        final String TOKEN = scanner.nextLine();

        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();
    }

}
