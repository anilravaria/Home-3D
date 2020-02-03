package com.example.mypc.homelayoutgenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ROOMDATA.db";
    public static final String TABLE_NAME = "ROOM";
    public static final String COL_1 = "LEN_ROOM";
    public static final String COL_2 = "WID_ROOM";
    public static final String COL_3 = "LEN_BED";
    public static final String COL_4 = "WID_BED";
    public static final String COL_5 = "LEN_CUPBOARD";
    public static final String COL_6 = "WID_CUPBOARD";
    public static final String COL_7 = "LEN_APPLIANCES";
    public static final String COL_8 = "WID_APPLIANCES";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( LEN_ROOM REAL ,WID_ROOM REAL, LEN_BED REAL,WID_BED REAL,LEN_CUPBOARD REAL,WID_CUPBOARD REAL,LEN_APPLIANCES REAL, WID_APPLIANCES REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(float len_room, float wid_room, float len_bed, float wid_bed, float len_cupboard, float wid_cupboard, float len_app, float wid_app) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.describeContents();
        contentValues.put(COL_1, len_room);
        contentValues.put(COL_2, wid_room);
        contentValues.put(COL_3, len_bed);
        contentValues.put(COL_4, wid_bed);
        contentValues.put(COL_5, len_cupboard);
        contentValues.put(COL_6, wid_cupboard);
        contentValues.put(COL_7, len_app);
        contentValues.put(COL_8, wid_app);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result != -1)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor re = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return re;
    }
    public boolean getLastRecord()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String SelQuery="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(SelQuery,null);

        return cursor.moveToLast();
    }
    public Cursor getExcelData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ",
                null);
        return res;
    }

}
