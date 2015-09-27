package elton.networker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreguzman on 9/26/15.
 */
public class CheckboxTextAdapter extends ArrayAdapter<CheckboxText> implements View.OnClickListener {

    private ArrayList<CheckboxText> checkboxTextList;

    public CheckboxTextAdapter(Context context, List<CheckboxText> objects) {
        super(context, 0, objects);
        checkboxTextList = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.add_connection_list_item_layout, null);
        }

        CheckboxText checkboxText = checkboxTextList.get(position);

        if (checkboxText != null) {
            CheckBox checkBoxText = (CheckBox) view.findViewById(R.id.addContactCheckbox);

            checkBoxText.setText(checkboxText.getText());
            checkBoxText.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox;
        if (view instanceof CheckBox) {
            checkBox = (CheckBox) view;

            for (CheckboxText c : checkboxTextList) {
                if (c.getText().equals(checkBox.getText())) {
                    c.setChecked(checkBox.isChecked());
                }
            }
        }
    }

}
