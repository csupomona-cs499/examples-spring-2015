package edu.cpp.cs499.l19_push_notification_parse;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        loadData();

        this.registerReceiver(new SyncActivityReceiver(), new IntentFilter("edu.cpp.cs499"));
    }

    private void loadData() {
        Log.i("push", "loading data " + this);
        final ProgressDialog progressDialog = ProgressDialog.show(
                MainActivity.this, "", "Please wait");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TeamProject");
        query.orderByAscending("name");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                List<String> res = new ArrayList<String>();
                for(ParseObject po : list) {
                    res.add(po.getString("name") + " : " + po.getString("member"));
                }
                listView.setAdapter(new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_list_item_1, res
                ));
                progressDialog.hide();
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

    class SyncActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("push", "sync broadcast received.");
            loadData();
        }
    }
}
