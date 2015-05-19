package edu.cpp.cs499.l20_camera;

import android.content.Context;
import android.hardware.Camera;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by yusun on 5/18/15.
 */
public class PhotoHandler implements Camera.PictureCallback {

    private final Context context;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        final ParseFile file = new ParseFile("monitor-photo.jpg", data);
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("Camera", e.getMessage(), e);
                } else {
                    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    String provider = locationManager.getBestProvider(criteria, false);
                    Location location = locationManager.getLastKnownLocation(provider);

                    ParseObject record = new ParseObject("MonitorPhoto");
                    record.put("lat", location.getLatitude());
                    record.put("lon", location.getLongitude());
                    record.put("applicantResumeFile", file);
                    record.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.e("Camera", e.getMessage(), e);
                            } else {
                                Log.i("Camera", "Sucessfully saved!");
                            }
                        }
                    });
                }
            }
        });
    }

}
