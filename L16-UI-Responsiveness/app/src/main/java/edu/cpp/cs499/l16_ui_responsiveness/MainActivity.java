package edu.cpp.cs499.l16_ui_responsiveness;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.badButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heavyComputation();
            }
        });

        Button button2 = (Button) findViewById(R.id.goodButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        heavyComputation();
                        return null;
                    }
                }.execute();
            }
        });

        List<MyData> dataList = new ArrayList<MyData>();
        dataList.add(new MyData("yu", "test1"));
        dataList.add(new MyData("y2u", "test1"));
        dataList.add(new MyData("y3u", "te23st1"));
        dataList.add(new MyData("y4u", "te32st1"));
        dataList.add(new MyData("y5u", "test1"));
        dataList.add(new MyData("y6u", "te32st1"));
        dataList.add(new MyData("y7u", "test1"));
        dataList.add(new MyData("y3u", "te23st321"));
        dataList.add(new MyData("y2u", "test1"));
        dataList.add(new MyData("y1u", "te23st1"));
        dataList.add(new MyData("y33u", "test1"));
        dataList.add(new MyData("y22u", "test1"));

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyListViewAdapter(this, dataList));
    }

    private void heavyComputation() {
        String res = "abc";
        for(int i = 0; i < 10000; i++) {
            res += "bdc";
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
