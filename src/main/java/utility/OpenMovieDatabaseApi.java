package utility;


import com.fasterxml.jackson.databind.ObjectMapper;
import utility.pojo.SearchedShow;
import utility.pojo.ShowBySearch;
import utility.pojo.Shows;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class OpenMovieDatabaseApi {


    public static SearchedShow requestBySearch(String title) {
        title = title.replaceAll(" ", "+");
        int responseCode;
        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=6df9a40a" + "&s=" + title + "&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            responseCode = httpConnection.getResponseCode();
            System.out.println(responseCode);
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, SearchedShow.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Shows requestByIDMovie(String id) {
        int responseCode;
        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=6df9a40a" + "&i=" + id + "&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            responseCode = httpConnection.getResponseCode();
            System.out.println(responseCode);
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Shows.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
