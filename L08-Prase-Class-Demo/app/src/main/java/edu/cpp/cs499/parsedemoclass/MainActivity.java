package edu.cpp.cs499.parsedemoclass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private TeamProject objectBeingUpdated = null;

    private ImageView imageView;
    private Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String id = getIntent().getStringExtra("id");
        if (id != null) {
            ParseQuery<TeamProject> query = ParseQuery.getQuery(TeamProject.class);
            query.whereEqualTo("objectId", id);
            query.findInBackground(new FindCallback<TeamProject>() {
                @Override
                public void done(List<TeamProject> list, ParseException e) {
                    TeamProject project = list.get(0);

                    TextView nameText = (TextView) findViewById(R.id.nameText);
                    TextView memberText = (TextView) findViewById(R.id.memberText);
                    TextView despText = (TextView) findViewById(R.id.despText);
                    nameText.setText(project.getName());
                    memberText.setText(project.getMember());
                    despText.setText(project.getDescription());

                    if (project.getTeamImage() != null) {
                        project.getTeamImage().getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                    objectBeingUpdated = project;
                }
            });
        }

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameText = (TextView) findViewById(R.id.nameText);
                TextView memberText = (TextView) findViewById(R.id.memberText);
                TextView despText = (TextView) findViewById(R.id.despText);

                String name = nameText.getText().toString();
                String memeber = memberText.getText().toString();
                String desp = despText.getText().toString();

                TeamProject tp = new TeamProject();
                if (objectBeingUpdated != null) {
                    tp = objectBeingUpdated;
                } else {
                    tp = new TeamProject();
                }

                tp.setName(name);
                tp.setMember(memeber);
                tp.setDescription(desp);

                if (imageBitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    final ParseFile parseFile = new ParseFile("teamimage.png", byteArray);
                    tp.setTeamImage(parseFile);
                    parseFile.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.i("TEST", "Image file is uploaded.");
                        }
                    });
                }

                tp.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Success")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // FIRE ZE MISSILES!
                                    }
                                });
                        builder.create().show();
                    }
                });


            }
        });


        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Button takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        imageView = (ImageView) findViewById(R.id.photoImageView);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

}
