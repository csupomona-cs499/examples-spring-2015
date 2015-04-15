package edu.cpp.cs499.l06_listview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class FriendActivity extends ActionBarActivity {

    private ArrayList<Friend> friends;
    private FriendAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String name = this.getIntent().getStringExtra("name");
        TextView text = (TextView) findViewById(R.id.friendNameText);
        text.setText(name);

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
        if (id == R.id.shareFriendItem) {
            return true;
        } else if (id == R.id.addFriendItem) {
            friends.add(new Friend("Yu", 50, R.drawable.issac));
            listAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
