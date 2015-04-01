package edu.cpp.cs499.l08_parse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.cpp.cs499.l08_parse.data.Professor;


/**
 * This activity handles both adding and updating professor data.
 */
public class AddActivity extends ActionBarActivity {


    @InjectView(R.id.submitButton)
    Button submitButton;

    @InjectView(R.id.editTextName)
    EditText nameEditText;
    @InjectView(R.id.editTextOffice)
    EditText officeEditText;
    @InjectView(R.id.fromTime)
    EditText fromTimeEditText;
    @InjectView(R.id.toTime)
    EditText toTimeEditText;

    boolean isUpdate;
    Professor loadedProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.inject(this);

        // if the objectId is passed, it means that we want to
        // update the
        String objectId = getIntent().getStringExtra("parseObjectId");
        if (objectId != null) {
            isUpdate = true;
            ParseQuery<Professor> query = ParseQuery.getQuery(Professor.class);
            query.getInBackground(objectId, new GetCallback<Professor>() {
                public void done(Professor professor, ParseException e) {
                    loadedProfessor = professor;
                    nameEditText.setText(professor.getName());
                    officeEditText.setText(professor.getOffice());
                    fromTimeEditText.setText(professor.getFromTime());
                    toTimeEditText.setText(professor.getToTime());
                }
            });
            submitButton.setText("Update Professor");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProfessorObjectFromForm();
            }
        });
    }

    private void addProfessorObjectFromForm() {
        String name = nameEditText.getText().toString();
        String office = officeEditText.getText().toString();
        String fromTime = fromTimeEditText.getText().toString();
        String toTime = toTimeEditText.getText().toString();

        Professor p = isUpdate ? loadedProfessor : new Professor();
        p.setName(name);
        p.setOffice(office);
        p.setFromTime(fromTime);
        p.setToTime(toTime);


        p.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                showConfirmation();
            }
        });
    }

    private void showConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setMessage(isUpdate ? "Professor updated successfully." : "Professor added successfully.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setTitle(isUpdate ? "Update Professor" : "Add Professor");
        builder.create().show();
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
