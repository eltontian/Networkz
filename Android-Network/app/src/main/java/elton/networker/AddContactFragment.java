package elton.networker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddContactFragment extends Fragment implements AdapterView.OnItemClickListener,
    View.OnClickListener {

    private View mView;
    private Button connectButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.add_contact_layout, container, false);
        connectButton = (Button) mView.findViewById(R.id.connectButton);
        connectButton.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) mView.findViewById(R.id.nearbyConnectionsList);
        listView.setOnItemClickListener(this);

        ArrayList<CheckboxText> checkboxList = MainActivity.nearbyConnectionsArray;
        if (checkboxList != null) {
            listView.setAdapter(new CheckboxTextAdapter(mView.getContext(), checkboxList));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO What happens when I click on a person?
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mView.getContext(), "Connect", Toast.LENGTH_LONG).show();
        // TODO What happens when I connect?
    }
}
