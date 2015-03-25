package cs499.cpp.edu.l04_basic_widgets;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class ImageViewActivity extends ActionBarActivity {

    private static final String TAG = "UIDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        final Button buttonNoControl = (Button) findViewById(R.id.noControl);
        buttonNoControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.getLayoutParams().width = ActionBar.LayoutParams.WRAP_CONTENT;
                imageView.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
                imageView.requestLayout();
            }
        });

        final Button buttonNoControlFirStart = (Button) findViewById(R.id.noControlStart);
        buttonNoControlFirStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                imageView.getLayoutParams().width = ActionBar.LayoutParams.WRAP_CONTENT;
                imageView.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
                imageView.requestLayout();
            }
        });

        final Button buttonfixedXY = (Button) findViewById(R.id.fixedXY);
        buttonfixedXY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.getLayoutParams().width = 500;
                imageView.getLayoutParams().height = 400;
                imageView.requestLayout();
            }
        });

        final Button buttonfixedXYCenter = (Button) findViewById(R.id.fixedXYCenter);
        buttonfixedXYCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.getLayoutParams().width = 500;
                imageView.getLayoutParams().height = 400;
                imageView.requestLayout();
            }
        });

        final Button buttonfixedXYFit = (Button) findViewById(R.id.fixedXYFit);
        buttonfixedXYFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.getLayoutParams().width = 500;
                imageView.getLayoutParams().height = 400;
                imageView.requestLayout();
            }
        });

        final Button buttonfixedXYCrop = (Button) findViewById(R.id.fixedXYCenterCrop);
        buttonfixedXYCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.getLayoutParams().width = 500;
                imageView.getLayoutParams().height = 400;
                imageView.requestLayout();
            }
        });

        Button button = (Button) findViewById(R.id.rateImageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageViewActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = ImageViewActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.rating_dialog, null);
                final TextView resultText = (TextView) view.findViewById(R.id.resultText);
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        resultText.setText(Float.toString(rating));
                    }
                });

                builder.setView(view)
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        });
                builder.create().show();
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
