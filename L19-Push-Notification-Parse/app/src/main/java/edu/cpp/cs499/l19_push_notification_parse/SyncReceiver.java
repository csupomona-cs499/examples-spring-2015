package edu.cpp.cs499.l19_push_notification_parse;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by yusun on 5/17/15.
 */
public class SyncReceiver extends ParsePushBroadcastReceiver {
    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        Log.i("push", "received message: " + intent.getExtras());
        context.sendBroadcast(new Intent("edu.cpp.cs499"));
    }
}
