package com.example.tisapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper
{
    public static final String dbName = "Tis.db";
    private SQLiteDatabase  db;
    public DBHelper(Context context)
    {
        super(context, dbName, null, 1);
    }
    public void onCreate (SQLiteDatabase database)
    {
        try
        {
            this.db= database;
            String Create_Log_History = "Create table LogHistory (Lid INTEGER PRIMARY KEY AUTOINCREMENT, TISURL TEXT);";
            String Create_Log_History2 = "Create table LogHistory2 (Lid INTEGER PRIMARY KEY AUTOINCREMENT, TISTIMEOUT TEXT);";

            db.execSQL(Create_Log_History);
            db.execSQL(Create_Log_History2);

        }
        catch(Exception ex )
        {
            Log.d("EXCEPTION", ex.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    public void command(String query)
    {
        if (db == null)
            db = getWritableDatabase();

        db.execSQL(query);
    }
    public Cursor getCursor(String query)
    {
        if (db == null)
        {
            db = getWritableDatabase();
        }
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void close()
    {
        if (db != null)
            db.close();
    }
}
