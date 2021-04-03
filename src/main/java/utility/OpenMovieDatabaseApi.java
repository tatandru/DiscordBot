package utility;


import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.jdbc.driver.json.JsonpParserWrapper;
import utility.pojo.Movie;
import utility.pojo.Series;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

public class OpenMovieDatabaseApi {


    public static void requestBySearch() {
        //query = query.replaceAll(" ", "+");
    }


    public static Movie requestByID(String id) {

        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=6df9a40a" + "&i=" + id + "&&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Movie.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Series requestByID(String id, String type) {
        try {
            URL url = new URL("http://www.omdbapi.com/?" + "apikey=6df9a40a" + "&i=" + id + "&&r=json&page=1");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            InputStream inputStream = httpConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();


            return objectMapper.readValue(inputStream, Series.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
