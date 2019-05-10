package com.example.test8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Song_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "create table song(id integer primary key autoincrement,"+
                "genre text,"+
                "title text,"+
                "content text)";
        try{
            db.execSQL(createSQL);
        }catch (Exception e){
            Log.e("onCreate","테이블 is uncreated");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropSQL = "drop table song";
        db.execSQL(dropSQL);
        onCreate(db);
    }
}

