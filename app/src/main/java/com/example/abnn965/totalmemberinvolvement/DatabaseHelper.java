package com.example.abnn965.totalmemberinvolvement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABNN965 on 8/25/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TotalMemberInvolvement.db";

    public static final String TABLE_NAME ="user_table";
    public static final String UCOL_1 = "ID";
    public static final String UCOL_2 = "NAME";
    public static final String UCOL_3 = "SURNAME";
    public static final String UCOL_4 = "EMAIL";
    public static final String UCOL_5 = "PASSWORD";
    public static final String UCOL_6 = "MOBILE";
    public static final String UCOL_7 = "PHYSICAL_ADDRESS";

    public static final String TABLE_NAME2 = "target_table";
    public static final String TCOL_1 = "ID";
    public static final String TCOL_2 = "NAME";
    public static final String TCOL_3 = "SURNAME";
    public static final String TCOL_4 = "EMAIL";
    public static final String TCOL_5 = "MOBILE";
    public static final String TCOL_6 = "PHYSICAL_ADDRESS";
    public static final String TCOL_7 = "USER_EMAIL_ADDRESS";

    public static final String TABLE_NAME3 = "days_table";
    public static final String DCOL_1 = "ID";
    public static final String DCOL_2 = "MONDAY";
    public static final String DCOL_3 = "TUESDAY";
    public static final String DCOL_4 = "WEDNESDAY";
    public static final String DCOL_5 = "THURSDAY";
    public static final String DCOL_6 = "FRIDAY";
    public static final String DCOL_7 = "SATURDAY";
    public static final String DCOL_8 = "SUNDAY";
    public static final String DCOL_9 = "TARGET_EMAIL";
    public static final String DCOL10 = "USER_EMAIL";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, PASSWORD TEXT, MOBILE TEXT, PHYSICAL_ADDRESS TEXT)");
        db.execSQL("create table "+TABLE_NAME2+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, MOBILE TEXT, PHYSICAL_ADDRESS TEXT, USER_EMAIL_ADDRESS TEXT)");
        db.execSQL("create table "+TABLE_NAME3+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, MONDAY TEXT, TUESDAY TEXT, WEDNESDAY TEXT, THURSDAY TEXT, FRIDAY TEXT, SATURDAY TEXT, SUNDAY TEXT, TARGET_EMAIL TEXT, USER_EMAIL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME3);
        onCreate(db);
    }

    public boolean insertUserDetails(String name, String surname, String email, String password, String mobile, String address){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UCOL_2, name);
        contentValues.put(UCOL_3, surname);
        contentValues.put(UCOL_4, email);
        contentValues.put(UCOL_5, password);
        contentValues.put(UCOL_6, mobile);
        contentValues.put(UCOL_7, address);

        long results = db.insert(TABLE_NAME, null, contentValues);

        if (results == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean captureTargetData(String name, String surname, String email, String mobile, String address, String userEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TCOL_2,name);
        contentValues.put(TCOL_3,surname);
        contentValues.put(TCOL_4,email);
        contentValues.put(TCOL_5,mobile);
        contentValues.put(TCOL_6, address);
        contentValues.put(TCOL_7, userEmail);
        long result = db.insert(TABLE_NAME2,null , contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean newCheckInTarget(String day1, String day2, String day3, String day4, String day5,String day6,String day7,String targetEmail, String userEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_2,day1);
        contentValues.put(DCOL_3,day2);
        contentValues.put(DCOL_4,day3);
        contentValues.put(DCOL_5,day4);
        contentValues.put(DCOL_6,day5);
        contentValues.put(DCOL_7,day6);
        contentValues.put(DCOL_8,day7);
        contentValues.put(DCOL_9,targetEmail);
        contentValues.put(DCOL10, userEmail);
        long result = db.insert(TABLE_NAME3,null , contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean checkInMonday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_2,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;

    }
    public boolean checkInTuesday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_3,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;

    }
    public boolean checkInWednesday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_4,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;

    }
    public boolean checkInThursday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_5,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;

    }
    public boolean checkInFriday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_6,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;
    }
    public boolean checkInSaturday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_7,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;
    }
    public boolean checkInSunday(int id,String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_8,day);

        return db.update(TABLE_NAME3,contentValues,DCOL_1+" = "+id,null) > 0;
    }
}
