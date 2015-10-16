package elton.networker;

import java.util.ArrayList;

/**
 * Created by elton on 10/16/15.
 */
public class ConnectionObject {


    ArrayList<TileObject> tiles;

    String originID;


    public ConnectionObject(String originID, ArrayList<TileObject> tiles) {
        super();

        this.tiles = tiles;
        this.originID = originID;

    }



    @Override

    public String toString() {

        return "[ConnectionObject] originID = " + originID + "tile = " + tiles.toString();

    }

}
