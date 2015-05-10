package edu.cpp.cs499.l13_data_storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import edu.cpp.cs499.l13_data_storage.data.Project;


public class MainActivityWithSharedPreferences extends ActionBarActivity {

    @InjectView(R.id.nameEdit)
    EditText nameEdit;
    @InjectView(R.id.projectEdit)
    EditText projectEdit;
    @InjectView(R.id.budgetEdit)
    EditText budgetEdit;

    @InjectView(R.id.resultList)
    ListView resultListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.addButton)
    void onClickAdd() {
        String name = nameEdit.getText().toString();
        String project = projectEdit.getText().toString();
        int budget = Integer.parseInt(budgetEdit.getText().toString());
        Project p = new Project(name, project, budget);
        persistProjectData(p);
    }

    @OnClick(R.id.searchButton)
    void onClickSearch() {
        List<Project> projects = loadAllProjects();
        if (projects != null) {
            resultListView.setAdapter(new ArrayAdapter<Project>(
                    this, android.R.layout.simple_list_item_1, projects
            ));
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

    private void persistProjectData(Project p) {
        List<Project> projectList = loadAllProjects();
        if (projectList == null) {
            projectList = new ArrayList<Project>();
        }
        projectList.add(p);
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String projectListStr = objectMapper.writeValueAsString(projectList);
            editor.putString("project-list", projectListStr);
            editor.commit();
        } catch (JsonProcessingException e) {
            Log.e("DB", "Failed to write the project list in the sp", e);
        }
    }

    private List<Project> loadAllProjects() {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String projectMapStr = sp.getString("project-list", null);
        List<Project> projectList = null;
        if (projectMapStr != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Log.i("DB", projectMapStr);
                projectList = objectMapper.readValue(projectMapStr, new TypeReference<List<Project>>() {});
            } catch (IOException e) {
                Log.e("DB", "Failed to parse the project map String" ,e);
            }
        }
        return projectList;
    }
}
