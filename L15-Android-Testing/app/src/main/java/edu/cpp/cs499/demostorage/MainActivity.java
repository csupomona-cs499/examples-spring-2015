package edu.cpp.cs499.demostorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.cpp.cs499.demostorage.db.ProjectDBHelper;
import edu.cpp.cs499.demostorage.db.ProjectTableEntry;


public class MainActivity extends ActionBarActivity {

    private SQLiteOpenHelper dbHelper;

    @InjectView(R.id.nameEdit)
    EditText nameEdit;
    @InjectView(R.id.memberEdit)
    EditText memberEdit;
    @InjectView(R.id.budgetEdit)
    EditText budgetEdit;
    @InjectView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        dbHelper = new ProjectDBHelper(this);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddButton();
            }
        });
        Button listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListButton();
            }
        });
    }

    void clickAddButton() {
        // add the record to SQLite DB
        String name = nameEdit.getText().toString();
        String member = memberEdit.getText().toString();
        int budget = Integer.parseInt(budgetEdit.getText().toString());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ProjectTableEntry.COLUMN_NAME_NAME, name);
        cv.put(ProjectTableEntry.COLUMN_NAME_MEMBER, member);
        cv.put(ProjectTableEntry.COLUMN_NAME_BUDGET, budget);

        long id = db.insert(ProjectTableEntry.TABLE_NAME, null, cv);
        Log.i("DB", "Insert successfully " + id);
    }

    void clickListButton() {
        // query the db
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {ProjectTableEntry.COLUMN_NAME_NAME,
                ProjectTableEntry.COLUMN_NAME_MEMBER,
                ProjectTableEntry.COLUMN_NAME_BUDGET};

        String selection = null;
        String[] selectionArgs = null;

        Cursor cursor = db.query(ProjectTableEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        List<Project> projectList = new ArrayList<Project>();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_NAME));
            String member = cursor.getString(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_MEMBER));
            int budget = cursor.getInt(cursor.getColumnIndex(ProjectTableEntry.COLUMN_NAME_BUDGET));

            Project p = new Project(name, member, budget);
            projectList.add(p);

            cursor.moveToNext();
        }

        listView.setAdapter(new ArrayAdapter<Project>(
                this, android.R.layout.simple_list_item_1, projectList
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
