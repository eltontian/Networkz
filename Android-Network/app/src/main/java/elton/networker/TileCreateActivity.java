package elton.networker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

    private final Activity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tile_create);
        createButton = (Button) findViewById(R.id.done_button);
        spinner = (Spinner) findViewById(R.id.tileCreateType);

        String[] list = new String[2];
        list[0] = "LinkedIn";
        list[1] = "Resume";

        ArrayAdapter<String> stringAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        spinner.setAdapter(stringAdapter);


        createButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                byte[] data = "Working at Parse is great!".getBytes();
                final ParseFile file = new ParseFile("resume.txt", data);

                file.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        // If successful add file to user and signUpInBackground
                        if(e==null){
                            ArrayList<ParseObject> tilesArray = new ArrayList<ParseObject>();
                            ParseUser user = ParseUser.getCurrentUser();
                            JSONArray tiles = user.getJSONArray("tiles");


                            ParseObject tile = new ParseObject("Tile");
                            tile.add("resume", file);

                            if(tiles != null){
                                tilesArray = (ArrayList) user.getList("tiles");
                            }

                            tilesArray.add(tile);
                            user.put("tiles",tilesArray);
                            user.saveInBackground();

                        }
                    }
                });

                Intent intent = new Intent(self, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}