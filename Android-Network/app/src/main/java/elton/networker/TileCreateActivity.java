package elton.networker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ui.ParseLoginBuilder;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Shows the user profile. This simple activity can function regardless of whether the user
 * is currently logged in.
 */
public class TileCreateActivity extends Activity {

    private Button createButton;
    private Spinner spinner;
    private EditText value;

    private final Activity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tile_create);
        createButton = (Button) findViewById(R.id.done_button);
        spinner = (Spinner) findViewById(R.id.tileCreateType);
        value = (EditText) findViewById(R.id.tileCreateValue);

        String[] list = new String[4];
        list[0] = "LinkedIn";
        list[1] = "Resume";
        list[2] = "Github";
        list[3] = "Email";

        ArrayAdapter<String> stringAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        spinner.setAdapter(stringAdapter);


        createButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<ParseObject> tilesArray = new ArrayList<ParseObject>();



                ParseUser user = ParseUser.getCurrentUser();
                ArrayList<ParseObject> tiles = new ArrayList<ParseObject>();
                if(user.getList("tiles") != null) {
                    tiles = (ArrayList) user.getList("tiles");
                }

                spinner = (Spinner) findViewById(R.id.tileCreateType);
                value = (EditText) findViewById(R.id.tileCreateValue);
                String text = spinner.getSelectedItem().toString();

                ParseObject tile = new ParseObject("tile");
                tile.put("type", text);
                tile.put("value", value.getText().toString());





                tiles.add(tile);

                user.put("tiles", tiles);
                user.saveInBackground();
                
                Intent intent = new Intent(self, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}