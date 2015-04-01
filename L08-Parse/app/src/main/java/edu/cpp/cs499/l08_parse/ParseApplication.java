package edu.cpp.cs499.l08_parse;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

import edu.cpp.cs499.l08_parse.data.Professor;

/**
 * Created by yusun on 3/31/15.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this,
                "oR3WTZlk2HuRz3QJb0RpQwBCG3EraBScts6MNgg2",
                "eh1FFMvRJoSfnuH12IxU9WnUF7AX1pPhh6jYThrF");

        // You need to register the subclass if you want to define
        // your own data class like Professor
        ParseObject.registerSubclass(Professor.class);

        Log.i("Application", "Initialized!");
    }

}
