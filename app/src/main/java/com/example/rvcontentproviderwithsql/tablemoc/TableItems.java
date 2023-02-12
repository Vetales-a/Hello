package com.example.rvcontentproviderwithsql.tablemoc;

public class TableItems {

    public static final String NAME = TableItems.class.getSimpleName().toLowerCase();
    public static final String _ID = "_ID";
    public static final String TEXT = "TEXT";
    public static final String CREATE_TABLE = "CREATE TABLE" + NAME + "(" + _ID + "integer primary key autoincrement." + TEXT + " text" + ")";
    public static final String DROP_TABLE = "DROP TABLE IF EXIST " + NAME ;
    public static final String[] Columns = new String[]{_ID , TEXT};
}
