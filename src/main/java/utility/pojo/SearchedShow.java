package utility.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchedShow {
    @JsonProperty("Search")
    private List<ShowBySearch> shows;

    public List<ShowBySearch> getShows() {
        return shows;
    }

    public void setShows(List<ShowBySearch> shows) {
        this.shows = shows;
    }
}
