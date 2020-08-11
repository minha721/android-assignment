package com.example.todo;

public class ListDBContract {
    private ListDBContract(){}

    public static final String TABLE_NAME = "todo";
    public static final String COL_LIST_NAME = "todo_title";

    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COL_LIST_NAME + " CHAR(20) PRIMARY KEY)";
    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME;
    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TABLE_NAME + " (" + COL_LIST_NAME + ") VALUES ";
    public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_LIST_NAME + " = ";
}
