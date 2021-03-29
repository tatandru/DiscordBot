package database;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Curse {
    @BsonId
    private ObjectId id;
    @BsonProperty("curses")
    private List<String> curses;

    public List<String> getCurses() {
        return curses;
    }

    public void setCurses(List<String> curses) {
        this.curses = curses;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
