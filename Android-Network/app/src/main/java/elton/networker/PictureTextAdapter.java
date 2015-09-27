package elton.networker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PictureTextAdapter extends ArrayAdapter<PictureText> {

    private ArrayList<PictureText> pictureTextList;

    public PictureTextAdapter(Context context, List<PictureText> objects) {
        super(context, 0, objects);
        pictureTextList = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.picture_text_list_item, null);
        }

        PictureText pictureText = pictureTextList.get(position);

        if (pictureText != null) {
            ImageView pictureTextImage = (ImageView) view.findViewById(R.id.pictureTextListItemImage);
            TextView pictureTextText = (TextView) view.findViewById(R.id.pictureTextListItemText);

            pictureTextImage.setBackgroundDrawable(getContext().getResources()
                    .getDrawable(pictureText.getIcon()));
            pictureTextText.setText(pictureText.getText());
        }

        return view;
    }
}
