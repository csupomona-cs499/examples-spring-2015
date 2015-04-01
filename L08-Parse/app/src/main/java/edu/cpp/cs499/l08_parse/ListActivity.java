package edu.cpp.cs499.l08_parse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.cpp.cs499.l08_parse.adapter.ProfessorListAdapter;
import edu.cpp.cs499.l08_parse.data.Professor;


public class ListActivity extends ActionBarActivity {

    @InjectView(R.id.searchListButton)
    Button searchButton;
    @InjectView(R.id.keywordName)
    EditText editText;

    @InjectView(R.id.professorList)
    ListView listView;

    ProfessorListAdapter listAdapter;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);

        loadAllProfessors(null);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keywordName = editText.getText().toString();
                loadAllProfessors(keywordName);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Test" , " Clicked!!!");
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                intent.putExtra("parseObjectId", listAdapter.getItem(position).getObjectId());
                startActivity(intent);
            }
        });
    }

    private void loadAllProfessors(String nameKeyword) {
        showProgressDialog();

        final ParseQuery<Professor> professors = ParseQuery.getQuery(Professor.class);

        if (nameKeyword != null) {
            professors.whereContains("name", nameKeyword);
        }

        professors.findInBackground(new FindCallback<Professor>() {
            public void done(List<Professor> professors, ParseException exception) {
                hideProgressDialog();

                listAdapter = new ProfessorListAdapter(ListActivity.this, professors);
                listView.setAdapter(listAdapter);
            }
        });
    }

    private void showProgressDialog() {
        progress = ProgressDialog.show(this, "Searching professors ...",
                "Please wait ...", true);
    }

    private void hideProgressDialog() {
        progress.dismiss();
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
