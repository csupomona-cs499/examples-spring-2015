package edu.cpp.cs499.l13_data_storage.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yusun on 5/10/15.
 */
public class ProjectDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Project.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProjectTableEntry.TABLE_NAME + " (" +
                    ProjectTableEntry._ID + " INTEGER PRIMARY KEY," +
                    ProjectTableEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ProjectTableEntry.COLUMN_NAME_PROJECT + TEXT_TYPE + COMMA_SEP +
                    ProjectTableEntry.COLUMN_NAME_BUDGET + INT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProjectTableEntry.TABLE_NAME;


    public ProjectDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

}
