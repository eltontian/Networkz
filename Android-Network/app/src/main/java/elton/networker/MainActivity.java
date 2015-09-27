package elton.networker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private User user;

    private ListView optionsList;
    private TextView profileName;
    private ImageButton drawerOpenButton;

    private DrawerLayout navigationDrawer;
    private PictureText[] optionsArray;

    public static ArrayList<Tile> tiles;
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
        profileName = (TextView) findViewById(R.id.profileNameText);

    //    drawerOpenButton = (ImageButton) findViewById(R.id.openDrawerButton);
    //    drawerOpenButton.setOnClickListener(this);

        optionsArray = new PictureText[3];
        optionsArray[1] = new PictureText(R.drawable.ic_launcher, "My Tiles");
        optionsArray[0] = new PictureText(R.drawable.ic_launcher, "Connections");
        optionsArray[2] = new PictureText(R.drawable.ic_launcher, "Logout");

        tiles = new ArrayList<>();
        tiles.add(new Tile("Person 1"));
        tiles.add(new Tile("Person 2"));
        tiles.add(new Tile("Person 3"));

        contactsList = new ArrayList<>();
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Aash Bhardwaj"));
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Brandon Grink"));
        contactsList.add(new PictureText(R.drawable.ic_launcher, "Rahul Menon"));

        userTiles = new ArrayList<>();
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 1"));
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 2"));
        userTiles.add(new PictureText(R.drawable.ic_launcher, "Tile 3"));

        getParseData();
        setUpNavigationDrawer();
        setupClosedActionBar();
    }

    private void setupClosedActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        View customNav = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null); // layout which contains your button.

        actionBar.setCustomView(customNav);
    }

    private void getParseData() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        user = new User(0, currentUser.getString("name"));
    }

    private void setUpNavigationDrawer() {
        optionsList.setAdapter(new PictureTextAdapter(this,
                new ArrayList<PictureText>(Arrays.asList(optionsArray))));

        profileName.setText(user.getName());
    }

    //P
    //ParseUser.logOut();
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 10) {
            //getAddConnections();
        } else if (i == 0) {
            setUpViewConnections();
        } else if (i == 1) {
            setUpAddTile();
        } else {
            ParseUser.logOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void setUpAddTile() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyTileFragment myTileFragment = new MyTileFragment();

        fragmentTransaction.replace(R.id.mainContentContainer, myTileFragment);
        fragmentTransaction.commit();
        activityState = MainActivityState.ADD_TILE;
        navigationDrawer.closeDrawer(Gravity.LEFT);
    }

    private void setUpViewConnections() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ViewContactFragment viewContactFragment = new ViewContactFragment();

        fragmentTransaction.replace(R.id.mainContentContainer, viewContactFragment);
        fragmentTransaction.commit();
        activityState = MainActivityState.VIEW_CONTACT;
        navigationDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onClick(View view) {
        if (navigationDrawer.isDrawerOpen(Gravity.LEFT)) {
            navigationDrawer.closeDrawer(Gravity.LEFT);
            setupClosedActionBar();
        } else {
            navigationDrawer.openDrawer(Gravity.LEFT);
            setupOpenActionBar();
        }
    }

    private void setupOpenActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        ((ImageButton) findViewById(R.id.openDrawerButton))
                .setBackground(getResources().getDrawable(R.drawable.common_signin_btn_icon_dark));

        View customNav = LayoutInflater.from(this).inflate(R.layout.action_bar_layout, null); // layout which contains your button.

        actionBar.setCustomView(customNav);
    }

    public enum MainActivityState { ADD_CONTACT, VIEW_CONTACT, ADD_TILE }
}
