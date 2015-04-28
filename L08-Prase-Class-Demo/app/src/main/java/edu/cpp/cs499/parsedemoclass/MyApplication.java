package edu.cpp.cs499.parsedemoclass;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by yusun on 4/22/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "uvXn047c4OEbmkZJE91crdYL8T5N3Wf6FCqvCjin", "qsxQBgaZVswo2uINkjaS0HHGf3PZbmI2XGLLyQXX");
        ParseObject.registerSubclass(TeamProject.class);
    }
}
