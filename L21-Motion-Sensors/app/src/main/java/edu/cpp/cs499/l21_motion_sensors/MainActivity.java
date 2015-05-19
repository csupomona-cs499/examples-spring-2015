package edu.cpp.cs499.l21_motion_sensors;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private int imageWidth;
    private int width;
    private int halfX;
    private int currentX;
    @InjectView(R.id.playerIcon)
    ImageView playerImage;
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text2)
    TextView text2;
    @InjectView(R.id.text3)
    TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // get all available sensors in this phone
        Log.i("TEST", mSensorManager.getSensorList(Sensor.TYPE_ALL).toString());

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(new SensorEventListener2() {
            @Override
            public void onFlushCompleted(Sensor sensor) {

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                // Axis of the rotation sample, not normalized yet.
                Float axisX = event.values[0];
                Float axisY = event.values[1];
                Float axisZ = event.values[2];
                text1.setText("X: " + axisX.toString());
                text2.setText("Y: " + axisY.toString());
                text3.setText("Z: " + axisZ.toString());

                movePlayer(axisX);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        },mSensor,SensorManager.SENSOR_DELAY_NORMAL);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        imageWidth = playerImage.getWidth();
    }

    private void movePlayer(float x) {
        float scale = Math.abs(x / 4.5f) > 1 ? 1f : Math.abs(x / 4.5f);
        float direction = x > 0 ? -1 : 1;

        int marginX = (int) ((width / 2 - imageWidth / 2) * (1 + direction * scale));
        Log.i("TEST", marginX + " " + imageWidth + " " + width);
        if (marginX > (width - imageWidth)) {
            marginX = width - imageWidth;
        }
        Log.i("TEST", marginX + " " + imageWidth + " " + width);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)playerImage.getLayoutParams();
        lp.setMargins(marginX, 0, 0, 0);
        playerImage.setLayoutParams(lp);
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
}
