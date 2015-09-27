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
import android.widget.ListView;

import java.util.ArrayList;

import elton.networker.R;

public class ViewContactFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.view_contact_layout, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) mView.findViewById(R.id.viewContactList);
        listView.setOnItemClickListener(this);

        ArrayList<PictureText> contactsList = MainActivity.contactsList;
        if (contactsList != null) {
            listView.setAdapter(new PictureTextAdapter(mView.getContext(), contactsList));
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
