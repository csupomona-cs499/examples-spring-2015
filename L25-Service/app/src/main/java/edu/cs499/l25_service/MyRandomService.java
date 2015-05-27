package edu.cs499.l25_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by yusun on 5/25/15.
 */
public class MyRandomService extends Service {

    // Binder given to clients
    private final IBinder mBinder = new MyRandomBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    class MyRandomBinder extends Binder {
        public MyRandomService getService() {
            return MyRandomService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

}
