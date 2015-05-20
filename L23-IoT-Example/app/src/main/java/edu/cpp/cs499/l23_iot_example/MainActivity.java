package edu.cpp.cs499.l23_iot_example;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;


public class MainActivity extends ActionBarActivity {

    interface PhotoAPI {
        @GET("/process/{userId}")
        public void getPhoto(@Path("userId")String userId, Callback<Response> callback);
    }

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://emolance.ngrok.io")
            .build();

    PhotoAPI api = restAdapter.create(PhotoAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Button button = (Button) findViewById(R.id.takeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "Please wait");
                progressDialog.show();
                api.getPhoto("test-user", new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        progressDialog.hide();
                        String url = null;
                        try {
                            url = readInputStreamAsString(response.getBody().in());
                            Log.i("TEST", "Success: " + url);
                            Picasso.with(MainActivity.this).load(url).into(imageView);
                        } catch (IOException e) {
                            Log.e("TEST", "Failed: " + e.getMessage(), e);
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("TEST", "Failed: " + error.getMessage(), error);
                        progressDialog.hide();
                    }
                });
            }
        });
    }

    public static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
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
