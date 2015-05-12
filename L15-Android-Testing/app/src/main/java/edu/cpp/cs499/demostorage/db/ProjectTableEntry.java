package edu.cpp.cs499.demostorage.db;

import android.provider.BaseColumns;

/**
 * Created by yusun on 5/11/15.
 */
public class ProjectTableEntry implements BaseColumns {

    public static final String TABLE_NAME = "project";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_MEMBER = "member";
    public static final String COLUMN_NAME_BUDGET = "budget";

}
