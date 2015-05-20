package edu.cpp.cs499.l22_ibeacon;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends ActionBarActivity implements BeaconConsumer {

    private static final String TAG = "Beacon";
    private BeaconManager beaconManager;
    private WebView webView;

    private String currentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);

        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        beaconManager.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {

                List<Beacon> beacons = new ArrayList<Beacon>();
                for (Beacon b : collection) {
                    beacons.add(b);
                }

                Collections.sort(beacons, new Comparator<Beacon>() {
                    @Override
                    public int compare(Beacon t1, Beacon t2) {
                        return (int) (t1.getDistance() * 1000 - t2.getDistance() * 1000);
                    }
                });

                if (beacons.size() > 0) {
                    Log.i(TAG, beacons.get(0).getId1() + " " + beacons.get(0).getId2() + beacons.get(0).getDistance());
                    Log.i(TAG, beacons.get(beacons.size()-1).getId1() + " " + beacons.get(beacons.size()-1).getId2() + beacons.get(beacons.size()-1).getDistance());
                    String id = beacons.get(0).getId2().toString();
                    if (id != currentId) {
                        currentId = id;
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadWebPage(currentId);
                            }
                        });
                    }
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void loadWebPage(String currentId) {
        String url = "http://www.cpp.edu";
        if (currentId.equals("29914")) {
            url = "https://github.com/csupomona-cs499";
        } else if (currentId.equals("595830")) {
            url = "http://schedule.cpp.edu/index.aspx";
        }
        webView.loadUrl(url);
    }

}
