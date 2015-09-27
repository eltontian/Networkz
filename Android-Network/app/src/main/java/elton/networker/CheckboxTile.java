package elton.networker;

/**
 * Created by andreguzman on 9/26/15.
 */
public class CheckboxTile {

    private String tileText;
    private boolean checked;

    public CheckboxTile(String text) {
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

}
