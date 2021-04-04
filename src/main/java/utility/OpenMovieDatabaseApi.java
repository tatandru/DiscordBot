package utility;


import com.fasterxml.jackson.databind.ObjectMapper;
import utility.pojo.SearchedShow;
import utility.pojo.Shows;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenMovieDatabaseApi {


    public static SearchedShow requestBySearch(String title, String apikey) {
        title = title.replaceAll(" ", "+");
        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=" + apikey + "&s=" + title + "&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, SearchedShow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Shows requestByID(String id, String apikey) {
        id = id.replaceAll(" ", "+");
        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=" + apikey + "&t=" + id + "&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Shows.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
