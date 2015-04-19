package edu.cpp.cs499.l07_http_network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.cpp.cs499.l07_http_network.data.WeatherData;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    private static final ObjectMapper mapper = new ObjectMapper() {{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }};

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5";
    private WeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        service = restAdapter.create(WeatherService.class);

        final EditText editText = (EditText) findViewById(R.id.cityNameEditText);
        final TextView tempText = (TextView) findViewById(R.id.tempText);
        final TextView humText = (TextView) findViewById(R.id.humidityText);

        // The action below uses the traditional HTTP Client to
        // trigger the HTTP request and parse the response
        Button button0 = (Button) findViewById(R.id.sendButtonTra);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Network communication must happen in a background thread
                AsyncTask<String, Void, WeatherData> getWeatherRequest
                        = new AsyncTask<String, Void, WeatherData>() {
                    @Override
                    protected WeatherData doInBackground(String... strings) {
                        String name = editText.getText().toString();
                        InputStream inputStream = null;
                        String result = "";
                        try {
                            URL url = new URL(ENDPOINT + "/weather?q=" + name);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                            inputStream = urlConnection.getInputStream();

                            if(inputStream != null) {
                                result = convertInputStreamToString(inputStream);
                            }

                            Log.i("TEST", result);
                            if (result != null) {
                                WeatherData weatherData = mapper.readValue(result, WeatherData.class);
                                return weatherData;
                            }
                        } catch (Exception e) {
                            Log.w("TEST", e);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(WeatherData weatherData) {
                        tempText.setText("Temperature: " + weatherData.getMain().getTemp());
                        humText.setText("Humidity: " + Float.toString(weatherData.getMain().getHumidity()));
                    }
                };
                getWeatherRequest.execute();

            }
        });

        // The action below uses Retrofit library to trigger
        // the HTTP request and convert the response into object
        // automatically
        Button button = (Button) findViewById(R.id.sendButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                service.getWeatherByCityName(name, new Callback<WeatherData>() {
                    @Override
                    public void success(WeatherData weatherData, Response response) {
                        tempText.setText("Temperature: " + weatherData.getMain().getTemp());
                        humText.setText("Humidity: " + Float.toString(weatherData.getMain().getHumidity()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("TEST", "Failed");
                    }
                });
            }
        });
    }

    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
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
