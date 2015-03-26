package edu.cpp.cs499.l06_menus;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        if (id == R.id.addClass) {
            Toast.makeText(this, "Add Class!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.dropClass) {
            Toast.makeText(this, "Drop Class!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.searchClass) {
            Toast.makeText(this, "Search Class!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.shareClass) {
            Toast.makeText(this, "Share Class!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Options");
            mode.getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.addClass: {
                    Toast.makeText(ItemActivity.this, "Add Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.dropClass: {
                    Toast.makeText(ItemActivity.this, "Drop Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.searchClass: {
                    Toast.makeText(ItemActivity.this, "Search Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.shareClass: {
                    Toast.makeText(ItemActivity.this, "Share Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    return false;
            }
            return true;
        }
    };

}
