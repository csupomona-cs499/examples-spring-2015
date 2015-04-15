package edu.cpp.cs499.l06_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<Friend> friends;
    private FriendAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init friends
        friends = new ArrayList<Friend>();
        friends.add(new Friend("Allison", 23, R.drawable.allison));
        friends.add(new Friend("Jason", 2, R.drawable.jason));
        friends.add(new Friend("Sean", 8, R.drawable.sean));
        friends.add(new Friend("Issac", 5, R.drawable.issac));
        friends.add(new Friend("Anthony", 2, R.drawable.anthony));
        friends.add(new Friend("Allison", 23, R.drawable.allison));
        friends.add(new Friend("Jason", 2, R.drawable.jason));
        friends.add(new Friend("Sean", 8, R.drawable.sean));
        friends.add(new Friend("Issac", 5, R.drawable.issac));
        friends.add(new Friend("Anthony", 2, R.drawable.anthony));
        friends.add(new Friend("Allison", 23, R.drawable.allison));
        friends.add(new Friend("Jason", 2, R.drawable.jason));
        friends.add(new Friend("Sean", 8, R.drawable.sean));
        friends.add(new Friend("Issac", 5, R.drawable.issac));
        friends.add(new Friend("Anthony", 2, R.drawable.anthony));

        ListView listView = (ListView) findViewById(R.id.listView);

        listAdapter = new FriendAdapter(
                this, friends);

        listView.setAdapter(listAdapter);


        Button add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friends.add(new Friend("Yu", 50, R.drawable.issac));
                listAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, FriendActivity.class);
                intent.putExtra("name", friends.get(i).getName());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                return true;
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
        if (id == R.id.shareFriendItem) {
            return true;
        } else if (id == R.id.addFriendItem) {
            friends.add(new Friend("Yu", 50, R.drawable.issac));
            listAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.shareFriendItem:
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };
}
