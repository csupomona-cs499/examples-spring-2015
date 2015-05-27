package edu.cs499.l25_service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by yusun on 5/25/15.
 */
public class MyService extends Service {

    private AsyncTask<Void, Void, Void> task;

    @Override
    public void onCreate() {
        super.onCreate();
        task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                for(int i = 0; i < 100 && !isCancelled(); i++) {
                    Log.i("TEST", "Running servcie: " + i + " " + isCancelled());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        task.cancel(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        task.execute();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
