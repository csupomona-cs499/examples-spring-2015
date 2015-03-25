package cs499.cpp.edu.l04_basic_widgets;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "UIDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. TextView
        final TextView textView = (TextView) findViewById(R.id.textViewLabel);
        String text = textView.getText().toString(); // get text
        Log.i(TAG, text);
        textView.setText(text + " (Running)");  // change text

        // 2. EditText
        final EditText editText = (EditText) findViewById(R.id.editTextDemo);
        Log.i(TAG, editText.getText().toString()); // get text

        // 3. Button
        final TextView resultView = (TextView) findViewById(R.id.textResult);
        Button button = (Button) findViewById(R.id.buttonGetText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(editText.getText().toString());
            }
        });


        // 4. Switch
        final TextView textViewWifiStatus = (TextView) findViewById(R.id.textViewWifi);
        final Switch switchControll = (Switch) findViewById(R.id.switchControll);
        switchControll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textViewWifiStatus.setText("WiFi is " + (isChecked ? "on" : "off"));
            }
        });

        // 5. SeekBar
        final TextView seekBarValue = (TextView) findViewById(R.id.currentValueText);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarDemo);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // 6. CheckBox
        final TextView textChecked = (TextView) findViewById(R.id.checkBoxText);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textChecked.setText("Is Checked? " + isChecked);
            }
        });

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
