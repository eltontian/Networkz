package elton.networker;

/**
 * Created by andreguzman on 9/26/15.
 */
public class Tile {

    private String tileText;
    private boolean checked;

    private TileType type;
    private String username;
    private String password;

    private String filePath;

    public Tile(String tileText, boolean checked, TileType type, String username, String password) {
        this.tileText = tileText;
        this.checked = checked;
        this.type = type;
        this.username = username;
        this.password = password;
        this.filePath = null;
    }

    public Tile(String tileText, boolean checked, TileType type, String filePath) {
        this.tileText = tileText;
        this.checked = checked;
        this.type = type;
        this.filePath = filePath;
        this.username = null;
        this.password = null;
    }

    public Tile(String text) {
        this.tileText = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void toggle() {
        this.checked = !this.checked;
    }

    public String getText() {
        return tileText;
    }

    public enum TileType { SOCIAL_MEIDA, RESUME, BUSINESS_CARD }

}
