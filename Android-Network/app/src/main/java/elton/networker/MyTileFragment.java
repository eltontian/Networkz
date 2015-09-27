package elton.networker;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import elton.networker.R;

public class MyTileFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View mView;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.add_tile_layout, container, false);
        spinner = (Spinner) mView.findViewById(R.id.newTileSpinner);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) mView.findViewById(R.id.tileList);
        listView.setOnItemClickListener(this);

        ArrayList<PictureText> tileList = MainActivity.userTiles;
        if (tileList != null) {
            listView.setAdapter(new PictureTextAdapter(mView.getContext(), tileList));
        }

        String[] list = new String[2];
        list[0] = "Facebook";
        list[1] = "LinkedIn";

        ArrayAdapter<String> stringAdapter =
                new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_list_item_1, list);

        spinner.setAdapter(stringAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
