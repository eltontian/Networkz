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

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

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
//        createButton = (Button) findViewById(R.id.done_button);
//        spinner = (Spinner) findViewById(R.id.tileCreateType);

//        String[] list = new String[2];
//        list[0] = "LinkedIn";
//        list[1] = "Resume";
//
//        ArrayAdapter<String> stringAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
//
//        spinner.setAdapter(stringAdapter);


//        createButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }


}