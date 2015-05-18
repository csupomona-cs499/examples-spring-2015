package edu.cpp.cs499.l19_push_notification_parse;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by yusun on 5/17/15.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "uvXn047c4OEbmkZJE91crdYL8T5N3Wf6FCqvCjin", "qsxQBgaZVswo2uINkjaS0HHGf3PZbmI2XGLLyQXX");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
