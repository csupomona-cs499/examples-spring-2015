package edu.cpp.cs499.l14_google_maps;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private GoogleMap map;
    private Marker myLocMarker;
    private List<ParseObject> allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);
                map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                        // You can update your location whenever the location is changed
                        // focusOnMarkers(ll);
                    }
                });
            }
        });

        final TextView nameText = (TextView) findViewById(R.id.nameText);
        final TextView textView = (TextView) findViewById(R.id.address);

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Geocoder geoCoder = new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocationName(
                            textView.getText().toString(), 5);
                    if (addresses.size() > 0) {

                        Double lat = (double) (addresses.get(0).getLatitude());
                        Double lon = (double) (addresses.get(0).getLongitude());

                        // add the record to Parse
                        addData(nameText.getText().toString(), lat, lon);

                        Log.d("lat-long", "" + lat + "......." + lon);
                        final LatLng user = new LatLng(lat, lon);
                        addNewMarker(nameText.getText().toString(), user);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button findButton = (Button) findViewById(R.id.findButton);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllData();
            }
        });

        Button drawButton = (Button) findViewById(R.id.drawButton);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawPolylines();
            }
        });

        // try to load the data from local store
        loadFromLocalParseStore();
    }

    private void drawPolylines() {

        PolylineOptions op = new PolylineOptions().geodesic(true);
        for(ParseObject object : allData) {
            op.add(new LatLng(object.getDouble("lat"), object.getDouble("lon")));
        }

        // Polylines are useful for marking paths and routes on the map.
        map.addPolyline(op);
    }

    private void addData(String name, double lat, double lon) {
        ParseObject object = new ParseObject("LocInfo");
        object.put("name", name);
        object.put("lat", lat);
        object.put("lon", lon);
        object.saveInBackground();
    }

    private void loadAllData() {
        Log.i("TEST", "Loading data remotely!");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LocInfo");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                allData = list;
                perisisLocationsInParseLocalStore();
                addAllMarkers();
            }
        });
    }

    private void addAllMarkers() {
        // build the markers
        List<MarkerOptions> ms = new ArrayList<MarkerOptions>();
        for (ParseObject object : allData) {
            MarkerOptions mo = new MarkerOptions()
                    .title(allData.indexOf(object) + ": " + object.get("name").toString())
                    .position(new LatLng(object.getDouble("lat"), object.getDouble("lon")));
            ms.add(mo);
        }

        // clear the map
        map.clear();
        // figure out the bound to use to show all the markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(MarkerOptions mo : ms) {
            Marker m = map.addMarker(mo);
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 10);
        map.animateCamera(cu);
    }

    private void addNewMarker(String name, LatLng ll) {
        if (myLocMarker != null) {
            myLocMarker.remove();
        }
        myLocMarker = map.addMarker(
                new MarkerOptions()
                        .position(ll)
                        .title(name));
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(ll, 12);
        map.animateCamera(cu);
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

    private void perisisLocationsInParseLocalStore() {
        if (allData != null) {
            ParseObject.pinAllInBackground(allData);
        }
    }

    private void loadFromLocalParseStore() {
        Log.i("TEST", "Loading data locally!");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LocInfo");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                allData = list;
                if (allData != null && allData.size() > 0) {
                    addAllMarkers();
                    loadAllData();
                }
            }
        });
    }

}
