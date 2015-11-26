package com.example.snr.errand_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "errandHelper.db";

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    private static final String TASK_TABLE_NAME = "tasks";
    private static final String TASK_ID = "task_id";
    private static final String TASK_NAME = "name";
    private static final String TASK_TYPE = "type";
    private static final String TASK_DESC = "description";
    private static final String TASK_CREATIONTIME = "creation_time";
    private static final String TASK_DUETIME = "due_time";
    private static final String TASK_CREATORID = "creator_id";
    private static final String TASK_WORKERID = "woker_id";
    private static final String TASK_STATUS = "status";

    SQLiteDatabase db;

    private static final String CREATE_USER_TABLE = "create table " + USER_TABLE_NAME + " ("
            + USER_ID + " integer primary key not null,"
            + USER_EMAIL + " text not null,"
            + USER_PASSWORD + " text not null)";

    private static final String CREATE_TASK_TABLE = "create table " + TASK_TABLE_NAME +" ("
            + TASK_ID + " integer primary key not null, "
            + TASK_NAME + " text not null,"
            + TASK_TYPE + " text not null,"
            + TASK_DESC + " text not null,"
            + TASK_CREATIONTIME + " date," // not null, hasn't implemented yet
            + TASK_DUETIME + " date,"
            + TASK_CREATORID + " integer," // not null, hasn't implemented yet
            + TASK_WORKERID + " integer,"
            + TASK_STATUS + " text)"; // not null, hasn't implemented yet
    //+ " foreign key (" + TASK_CREATORID + ") references users)"; not implemented yet. note the parenthesis!!

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
        this.db = db;
    }

    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(USER_ID, count);
        u.setUserID(count);
        values.put(USER_EMAIL, u.getEmail());
        values.put(USER_PASSWORD, u.getPassword());
        db.insert(USER_TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String str){
        db = this.getReadableDatabase();
        String query = "select email, password from "+ USER_TABLE_NAME;
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

    public void insertTask(Task t) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String query = "select * from tasks";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(TASK_ID, count);
        t.setTaskID(count);
        values.put(TASK_NAME, t.getName());
        values.put(TASK_DESC, t.getDesc());
        values.put(TASK_TYPE, t.getType());
//        values.put(TASK_STATUS, t.getStatus());
//        values.put(TASK_CREATORID, t.getCreator());
//        values.put(TASK_DUETIME, dateFormat.format(t.getDueTime()));
//        values.put(TASK_CREATIONTIME, dateFormat.format(t.getCreationTime()));

        db.insert(TASK_TABLE_NAME, null, values);
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE_NAME, TASK_ID + " = " + Integer.toString(id),null);
    }

    public Cursor queryCursor() {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id,* from tasks", null);
        return c;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS "+ USER_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
