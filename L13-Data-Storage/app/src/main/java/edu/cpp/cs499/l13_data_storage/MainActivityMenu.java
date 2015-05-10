package edu.cpp.cs499.l13_data_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.cpp.cs499.l13_data_storage.data.Project;


public class MainActivityMenu extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.photoButton1)
    void onClickAdd1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.photoButton2)
    void onClickAdd2() {
        Intent intent = new Intent(this, MainActivityWithSharedPreferences.class);
        startActivity(intent);
    }

    @OnClick(R.id.photoButton3)
    void onClickAdd3() {
        Intent intent = new Intent(this, MainActivityWithExternalStorage.class);
        startActivity(intent);
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
