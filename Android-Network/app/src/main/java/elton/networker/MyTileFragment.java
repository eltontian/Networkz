package elton.networker;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;

import elton.networker.R;

public class MyTileFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View mView;
    private Button captureButton;
    private Button generateButton;
    private View popup;
    private ImageView qr_img;
    private Dialog settingsDialog;
    private ImageButton imageButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.add_tile_layout, container, false);

        imageButton = (ImageButton) mView.findViewById(R.id.newTileButton);

        captureButton = (Button) mView.findViewById(R.id.cameraButton);
        generateButton = (Button) mView.findViewById(R.id.generateButton);
        popup = inflater.inflate(R.layout.image_layout, null);
        qr_img = (ImageView) popup.findViewById(R.id.qr_img);
        settingsDialog = new Dialog(this.getActivity());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        settingsDialog.setContentView(popup);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) mView.findViewById(R.id.tileList);
        listView.setOnItemClickListener(this);

        captureButton.setOnClickListener(this);
        generateButton.setOnClickListener(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mView.getContext(), TileCreateActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<PictureText> tileList = MainActivity.userTiles;
        if (tileList != null) {
            listView.setAdapter(new PictureTextAdapter(mView.getContext(), tileList));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO start activity to edit data
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
        QRCodeWriter writer = new QRCodeWriter();

        String user = ParseUser.getCurrentUser().getUsername();
        String content = user + ":";

        for (Tile t : MainActivity.tiles) {
            if (t.isChecked()) {
                user = user + t.getText() + "_";
            }
        }

        final Fragment self = this;
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            if(bmp == null){
                Log.d("Checking Bitmap", "bMap is null");
            }
            qr_img.setImageBitmap(bmp);
            settingsDialog.show();

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
