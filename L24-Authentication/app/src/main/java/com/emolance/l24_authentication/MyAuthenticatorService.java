package com.emolance.l24_authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by yusun on 5/25/15.
 */
public class MyAuthenticatorService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TEST", "binded executed!");
        MyAuthenticator authenticator = new MyAuthenticator(this);
        return authenticator.getIBinder();
    }
}
