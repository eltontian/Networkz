package elton.networker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreguzman on 9/26/15.
 */
public class TileAdapter extends ArrayAdapter<Tile> implements View.OnClickListener {

    private ArrayList<Tile> tileList;

    public TileAdapter(Context context, List<Tile> objects) {
        super(context, 0, objects);
        tileList = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tile_layout, null);
        }

        Tile tile = tileList.get(position);

        if (tile != null) {
            CheckBox checkBoxText = (CheckBox) view.findViewById(R.id.tileCheckbox);
            ImageButton editTileButton = (ImageButton) view.findViewById(R.id.editTileButton);

            checkBoxText.setText(tile.getText());
            checkBoxText.setOnClickListener(this);

            editTileButton.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox;
        if (view instanceof CheckBox) {
            checkBox = (CheckBox) view;

            for (Tile c : tileList) {
                if (c.getText().equals(checkBox.getText())) {
                    c.toggle();
                }
            }
        }
    }

}
