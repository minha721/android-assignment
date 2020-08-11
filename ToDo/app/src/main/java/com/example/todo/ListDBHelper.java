package com.example.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ListDBHelper extends SQLiteOpenHelper {

    private static volatile ListDBHelper instance;
    private static final String DB_NAME = "list_db";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db = null;

    private ListDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        if(db == null) {
            db = getWritableDatabase();
        }
    }

    public static ListDBHelper getInstance(Context context){
        if(instance == null){
            synchronized (ListDBHelper.class){
                if(instance == null)
                    instance = new ListDBHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ListDBContract.SQL_CREATE_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(ListDBContract.SQL_DROP_TBL);
        onCreate(db);
    }

    public void insert(Todo todo){
        db.execSQL(ListDBContract.SQL_INSERT + "(" + '"' + todo.getList() + '"' + ")");
    }

    public void delete(String title){
        db.execSQL(ListDBContract.SQL_DELETE + '"' + title + '"');
    }

    public ArrayList<Todo> getAll(){
        ArrayList<Todo> tmp = new ArrayList<>();
        db=getReadableDatabase();

        Cursor cursor;
        cursor = db.rawQuery(ListDBContract.SQL_SELECT, null);

        while(cursor.moveToNext()){
            String txtList = cursor.getString(0);

            Todo data = new Todo(txtList);
            tmp.add(data);
        }
        return tmp;
    }
}
