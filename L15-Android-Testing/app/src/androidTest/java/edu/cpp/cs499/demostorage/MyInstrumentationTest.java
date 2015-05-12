package edu.cpp.cs499.demostorage;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by yusun on 5/12/15.
 */
public class MyInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;

    private EditText nameEdit;
    private EditText memberEdit;
    private EditText budgetEdit;

    private Button addButton;
    private Button listButton;
    private ListView listView;

    public MyInstrumentationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();

        addButton = (Button) mainActivity.findViewById(R.id.addButton);
        listButton = (Button) mainActivity.findViewById(R.id.listButton);
        listView = (ListView) mainActivity.findViewById(R.id.listView);

        nameEdit = (EditText) mainActivity.findViewById(R.id.nameEdit);
        memberEdit = (EditText) mainActivity.findViewById(R.id.memberEdit);
        budgetEdit = (EditText) mainActivity.findViewById(R.id.budgetEdit);
    }

    @UiThreadTest
    public void testAddButton() {

        listButton.callOnClick();

        int initNumber = listView.getAdapter().getCount();

        nameEdit.setText("demo");
        memberEdit.setText("yu");
        budgetEdit.setText("100");

        addButton.callOnClick();

        listButton.callOnClick();

        int afterNumber = listView.getAdapter().getCount();

        Project p = (Project) listView.getAdapter().getItem(listView.getAdapter().getCount() - 1);

        assertEquals(afterNumber, initNumber + 1);
        assertEquals("demo", p.getName());
        assertEquals("yu", p.getMember());
        assertEquals(100, p.getBudget());
    }
}
