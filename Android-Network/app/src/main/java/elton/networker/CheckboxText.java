package elton.networker;

/**
 * Created by andreguzman on 9/26/15.
 */
public class CheckboxText {

    private String mText;
    private boolean checked;

    public CheckboxText(String text) {
        this.mText = text;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return mText;
    }

}
