package edu.cpp.cs499.l06_menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.classList);
        ListAdapter adpater = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, new String[]{"CS240", "CS356", "CS480", "CS499"});
        listView.setAdapter(adpater);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                startActionMode(modeCallBack);
                view.setSelected(true);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(intent);
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
                    Toast.makeText(MainActivity.this, "Add Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.dropClass: {
                    Toast.makeText(MainActivity.this, "Drop Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.searchClass: {
                    Toast.makeText(MainActivity.this, "Search Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.shareClass: {
                    Toast.makeText(MainActivity.this, "Share Class!", Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    return false;
            }
            return true;
        }
    };

}
