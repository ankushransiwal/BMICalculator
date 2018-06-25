package com.example.persisshah.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Persisshah on 1/12/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;
    Context context;

    public DatabaseHandler(Context context)
    {
        super(context,"History", null, 1);
        this.context = context;
        db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String s1 = "CREATE TABLE History (record TEXT)";
        db.execSQL(s1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS History");
        onCreate(db);

    }

    public void addHistory(String record)
    {
        ContentValues cv  = new ContentValues();
        cv.put("record",record);
        Long rid = db.insert("History",null,cv);
        if (rid<0)
        {
            Toast.makeText(context, "Insert Issue", Toast.LENGTH_SHORT).show();
        }else

        {
            Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show();
        }
    }

    public String getHistory()
    {
        StringBuffer ab = new StringBuffer();
        Cursor cursor= db.query("History",null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
           do {
               String record = cursor.getString(0);
               ab.append(record + "\n");
           }while(cursor.moveToNext());
        }
        else

        {
            return  "No Records To Display";
        }
        return  ab.toString();
    }
}
