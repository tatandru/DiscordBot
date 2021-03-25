package utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WolframAlphaSimpleApi {
    private String baseUrl = "http://api.wolframalpha.com/v1/simple?";
    private String input;

    public static File apiRequest(String apiKey, String query) {
        File imageResponse = new File("response.jpg");
        int responseCode = 0;
        query = query.replaceAll(" ", "+");
        try {
            URL url = new URL("http://api.wolframalpha.com/v1/simple?appid=" + apiKey + "&i=" + query);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            responseCode = httpConnection.getResponseCode();
            if (responseCode == 501) {
                return new File("error 501.jpg");
            } else {
                InputStream inputStream = httpConnection.getInputStream();
                Image image = ImageIO.read(inputStream);

                ImageIO.write((RenderedImage) image, "jpg", imageResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageResponse;
    }
}
