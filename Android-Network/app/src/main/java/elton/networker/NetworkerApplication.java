package elton.networker;

import android.app.Application;

import com.parse.Parse;

public class NetworkerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required - Initialize the Parse SDK
        Parse.initialize(this);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

    }
}