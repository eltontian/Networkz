package elton.networker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import elton.networker.R;

public class MyTileFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View mView;
    private Spinner spinner;
    private Button captureButton;
    private Button generateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.add_tile_layout, container, false);

        spinner = (Spinner) mView.findViewById(R.id.newTileSpinner);
        captureButton = (Button) mView.findViewById(R.id.cameraButton);
        generateButton = (Button) mView.findViewById(R.id.generateButton);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) mView.findViewById(R.id.tileList);
        listView.setOnItemClickListener(this);

        captureButton.setOnClickListener(this);
        generateButton.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (view.equals(captureButton)) {
            captureQRString();
        } else if (view.equals(generateButton)) {
            generateQRCode();
        }
    }

    private void captureQRString() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    private void setUpAddConnections(String username) {
        if (username != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AddContactFragment addContactFragment = new AddContactFragment();

            fragmentTransaction.replace(R.id.mainContentContainer, addContactFragment);
            fragmentTransaction.commit();
        } else {
            // TODO Handle an error
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String contents = null;

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                contents = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(mView.getContext(), contents, Toast.LENGTH_LONG).show();
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(mView.getContext(), "QR Code Error", Toast.LENGTH_LONG).show();
            }
        }


    }


    private void generateQRCode() {

    }
}
