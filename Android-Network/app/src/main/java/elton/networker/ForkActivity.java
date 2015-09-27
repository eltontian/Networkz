package elton.networker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ForkActivity extends Activity implements View.OnClickListener{

    private ImageButton addContactButton;
    private ImageButton viewContactButton;
    private ImageButton addTileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fork);

        addContactButton = (ImageButton) findViewById(R.id.addContactButton);
        viewContactButton = (ImageButton) findViewById(R.id.viewContactButon);
        addTileButton = (ImageButton) findViewById(R.id.addTileButton);

        addContactButton.setOnClickListener(this);
        viewContactButton.setOnClickListener(this);
        addTileButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addContactButton) {
            Intent intent = new Intent(this, AddContactFragment.class);
            this.startActivity(intent);
        } else if (view.getId() == R.id.viewContactButon) {
            Intent intent = new Intent(this, ViewContactFragment.class);
            this.startActivity(intent);
        } else if (view.getId() == R.id.addTileButton){
            Intent intent = new Intent(this, MyTileFragment.class);
            this.startActivity(intent);
        }
    }
}
