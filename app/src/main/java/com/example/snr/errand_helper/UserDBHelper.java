package com.example.snr.errand_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USERID = "user_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " ("
            + COLUMN_USERID + " integer primary key not null,"
            + COLUMN_EMAIL + " text not null,"
            + COLUMN_PASSWORD + " text not null)";

    public UserDBHelper(Context context){
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_USERID, count);
        u.setUserID(count);
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_PASSWORD, u.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String str){
        db = this.getReadableDatabase();
        String query = "select email, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String email,pass;
        pass = "not found";
        if(cursor.moveToFirst()){
            do{
                email = cursor.getString(0);
                if(email.equals(str)){
                    pass = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }

        return pass;
    }

    public void insertTask(Task t){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
