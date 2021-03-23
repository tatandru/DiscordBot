package utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class WolframAlphaSimpleApi {
    private String baseUrl="http://api.wolframalpha.com/v1/simple?";
    private String input;

    public static File apiRequest(String apiKey, String query){
        File imageResponse=new File("response.jpg");
        File errorResponse=new File("error 501.jpg");
        AtomicReference<Boolean> error= new AtomicReference<>(false);
        AtomicInteger responseCode = new AtomicInteger();
        String finalQuery = query.replaceAll(" ","+");;
        new Thread(() -> {
            try{
                URL url = new URL("http://api.wolframalpha.com/v1/simple?appid="+apiKey+"&i="+ finalQuery);
                HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
                httpConnection.setDoOutput(true);
                httpConnection.setRequestMethod("GET");
                responseCode.set(httpConnection.getResponseCode());
                if(responseCode.get() ==501){
                    error.set(true);
                }else{
                    InputStream inputStream= httpConnection.getInputStream();
                    Image image = ImageIO.read(inputStream);

                    ImageIO.write((RenderedImage) image,"jpg",imageResponse);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        });
        if(error.get()){
            return errorResponse;
        }else{
            return imageResponse;
        }

    }
}
