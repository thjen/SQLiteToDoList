package com.example.qthjen.sqlitetodolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /** Truy vấn không trả kết quả: CREATE, INSERT, UPDATE, DELETE **/
    public void QueryData(String sql) {
        SQLiteDatabase dataBase = getWritableDatabase();
        dataBase.execSQL(sql);
    }

    /** Truy vấn trả kết quả: SELECT **/
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
