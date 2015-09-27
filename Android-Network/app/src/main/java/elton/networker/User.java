package elton.networker;

/**
 * Created by andreguzman on 9/27/15.
 */
public class User {
    private String name;
    private int icon;

    public User(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public int getIcon() {
        return icon;
    }
}
