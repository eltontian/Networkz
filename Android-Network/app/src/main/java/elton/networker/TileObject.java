package elton.networker;

/**
 * Created by elton on 10/16/15.
 */
public class TileObject {

    String type;
    String value;


    public TileObject(String type, String value) {
        super();
        this.type = type;
        this.value = value;

    }


    @Override

    public String toString() {

        return "[TileObject] type=" + type + ",value=" + value;

    }
}
