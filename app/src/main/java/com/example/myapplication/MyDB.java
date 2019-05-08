package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME= "ExampleDB.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_PERSON= "Persons";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public MyDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create Table " + TABLE_PERSON + " ("+ COL_1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2 +" TEXT, "+ COL_3 +" TEXT, "+COL_4+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop  table If Exists " + TABLE_PERSON);
        onCreate(db);
    }

    public boolean AddData (String Name, String Surname, String Marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,Name);
        cv.put(COL_3,Surname);
        cv.put(COL_4,Marks);
        long result = db.insert(TABLE_PERSON , null , cv);
        if(result == -1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public  Cursor ShowData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("Select * from " + TABLE_PERSON,null);
        return  cs;
    }

    public boolean UpdateData(String Id, String Name, String Surname, String Marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,Id);
        cv.put(COL_2,Name);
        cv.put(COL_3,Surname);
        cv.put(COL_4,Marks);
        db.update(TABLE_PERSON,cv,"ID = ?", new String[]{Id});
        return  true;
    }

    public Integer DeleteData(String Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PERSON,"ID = ?", new String[]{Id});
    }
}
