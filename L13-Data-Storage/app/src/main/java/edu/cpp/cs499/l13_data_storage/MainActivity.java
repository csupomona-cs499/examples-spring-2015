package edu.cpp.cs499.l13_data_storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import edu.cpp.cs499.l13_data_storage.data.Project;
import edu.cpp.cs499.l13_data_storage.data.db.ProjectDBHelper;
import edu.cpp.cs499.l13_data_storage.data.db.ProjectTableEntry;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.nameEdit)
    EditText nameEdit;
    @InjectView(R.id.projectEdit)
    EditText projectEdit;
    @InjectView(R.id.budgetEdit)
    EditText budgetEdit;

    @InjectView(R.id.resultList)
    ListView resultListView;

    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        dbHelper = new ProjectDBHelper(this);
    }

    @OnClick(R.id.addButton)
    void onClickAdd() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(ProjectTableEntry.COLUMN_NAME_NAME, nameEdit.getText().toString());
        cvs.put(ProjectTableEntry.COLUMN_NAME_PROJECT, projectEdit.getText().toString());
        cvs.put(ProjectTableEntry.COLUMN_NAME_BUDGET, Integer.parseInt(budgetEdit.getText().toString()));
        long id = db.insert(ProjectTableEntry.TABLE_NAME, null, cvs);
        Log.i("DB", "Inserted the record successfully. " + id);
    }

    @OnClick(R.id.searchButton)
    void onClickSearch() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                ProjectTableEntry.COLUMN_NAME_NAME,
                ProjectTableEntry.COLUMN_NAME_PROJECT,
                ProjectTableEntry.COLUMN_NAME_BUDGET};
        String selection = ProjectTableEntry.COLUMN_NAME_BUDGET + " >= ?";
        String[] selectionArgs = { "1000" };

        Cursor cursor = db.query(ProjectTableEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        List<Project> projects = new ArrayList<Project>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_NAME));
            String project = cursor.getString(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_PROJECT));
            int budget = cursor.getInt(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_BUDGET));
            projects.add(new Project(name, project, budget));
            cursor.moveToNext();
        }

        resultListView.setAdapter(new ArrayAdapter<Project>(
                this, android.R.layout.simple_list_item_1, projects
        ));
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
