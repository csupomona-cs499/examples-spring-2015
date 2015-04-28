package edu.cpp.cs499.parsedemoclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;


public class SearchActivity extends ActionBarActivity {

    ListView listView;
    TeamProjectAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                TeamProject project = listAdapter.getItem(i);
                intent.putExtra("id", project.getObjectId());
                startActivity(intent);
            }
        });

        final EditText searchText = (EditText) findViewById(R.id.searchText);
        ImageButton searchButon = (ImageButton) findViewById(R.id.searchButton2);
        searchButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = searchText.getText().toString();
                //
                ParseQuery<TeamProject> query = ParseQuery.getQuery(TeamProject.class);
                query.whereContains("name", keyword);
                query.findInBackground(new FindCallback<TeamProject>() {
                    @Override
                    public void done(List<TeamProject> list, ParseException e) {
                        listAdapter = new TeamProjectAdapter(SearchActivity.this, list);
                        listView.setAdapter(listAdapter);
                    }
                });
            }
        });

        syncList();
    }

    private void syncList() {
        ParseQuery<TeamProject> query = ParseQuery.getQuery(TeamProject.class);
        query.findInBackground(new FindCallback<TeamProject>() {
            @Override
            public void done(List<TeamProject> list, ParseException e) {
                listAdapter = new TeamProjectAdapter(SearchActivity.this, list);
                listView.setAdapter(listAdapter);
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
