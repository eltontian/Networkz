package elton.networker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView optionsList;
    private DrawerLayout navigationDrawer;
    private PictureText[] optionsArray;
    public static ArrayList<CheckboxText> nearbyConnectionsArray;
    public static ArrayList<PictureText> contactsList;
    public static ArrayList<PictureText> userTiles;
    private MainActivityState activityState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawer = (DrawerLayout) findViewById(R.id.mainActivity);

        optionsList = (ListView) findViewById(R.id.navigationDrawerMenuList);
        optionsList.setOnItemClickListener(this);

        optionsArray = new PictureText[3];
        optionsArray[1] = new PictureText(R.drawable.ic_launcher, "Add Connection");
        optionsArray[2] = new PictureText(R.drawable.ic_launcher, "Add Tile");
        optionsArray[0] = new PictureText(R.drawable.ic_launcher, "View Connections");

        nearbyConnectionsArray = new ArrayList<>();
        nearbyConnectionsArray.add(new CheckboxText("Person 1"));
        nearbyConnectionsArray.add(new CheckboxText("Person 2"));
        nearbyConnectionsArray.add(new CheckboxText("Person 3"));

        contactsList = new ArrayList<>();
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Person 1"));
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Person 2"));
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Person 3"));

        userTiles = new ArrayList<>();
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 1"));
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 2"));
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 3"));

        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        optionsList.setAdapter(new PictureTextAdapter(this,
                new ArrayList<PictureText>(Arrays.asList(optionsArray))));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 1) {
            setUpAddConnections();
        } else if (i == 2) {
            setUpViewConnections();
        } else { // i == 2
            setUpAddTile();
        }
    }

    private void setUpAddConnections() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddContactFragment addContactFragment = new AddContactFragment();

        fragmentTransaction.replace(R.id.mainContentContainer, addContactFragment);
        fragmentTransaction.commit();
        activityState = MainActivityState.ADD_CONTACT;
        navigationDrawer.closeDrawer(Gravity.LEFT);
    }

    private void setUpViewConnections() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyTileFragment myTileFragment = new MyTileFragment();

        fragmentTransaction.replace(R.id.mainContentContainer, myTileFragment);
        fragmentTransaction.commit();
        activityState = MainActivityState.ADD_TILE;
        navigationDrawer.closeDrawer(Gravity.LEFT);
    }

    private void setUpAddTile() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ViewContactFragment viewContactFragment = new ViewContactFragment();

        fragmentTransaction.replace(R.id.mainContentContainer, viewContactFragment);
        fragmentTransaction.commit();
        activityState = MainActivityState.VIEW_CONTACT;
        navigationDrawer.closeDrawer(Gravity.LEFT);
    }

    public enum MainActivityState { ADD_CONTACT, VIEW_CONTACT, ADD_TILE }
}
