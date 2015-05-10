package edu.cpp.cs499.l13_data_storage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivityWithExternalStorage extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.photoButton1)
    void onClickPhotoInternal() {
        dispatchTakePictureIntent(false);
    }

    @OnClick(R.id.photoButton2)
    void onClickPhotoExternal() {
        dispatchTakePictureIntent(true);
    }

    private File createImageFile(boolean isExternal) throws IOException {
        // Create an image file name
        String imageFileName = "MyApp_" + System.currentTimeMillis();

        File storageDir =
                isExternal ?
                        Environment.getExternalStorageDirectory()
                    :
                        // this will use app internal storage, but
                        // it does not work for taking photos using
                        // the current approach, because the camera
                        // app cannot access the internal folder.
                        this.getFilesDir();

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        Log.i("File", "Createda a file at: " + image.getAbsolutePath());
        return image;
    }

    private void dispatchTakePictureIntent(boolean isExternal) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(isExternal);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Log.i("File", Uri.fromFile(photoFile).toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
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
