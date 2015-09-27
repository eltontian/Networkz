package elton.networker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreguzman on 9/26/15.
 */
public class CheckboxTileAdapter extends ArrayAdapter<CheckboxTile> implements View.OnClickListener {

    private ArrayList<CheckboxTile> checkboxTileList;

    public CheckboxTileAdapter(Context context, List<CheckboxTile> objects) {
        super(context, 0, objects);
        checkboxTileList = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.add_connection_list_item_layout, null);
        }

        CheckboxTile checkboxTile = checkboxTileList.get(position);

        if (checkboxTile != null) {
            CheckBox checkBoxText = (CheckBox) view.findViewById(R.id.addContactCheckbox);

            checkBoxText.setText(checkboxTile.getText());
            checkBoxText.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox;
        if (view instanceof CheckBox) {
            checkBox = (CheckBox) view;

            for (CheckboxTile c : checkboxTileList) {
                if (c.getText().equals(checkBox.getText())) {
                    c.setChecked(checkBox.isChecked());
                }
            }
        }
    }

}
