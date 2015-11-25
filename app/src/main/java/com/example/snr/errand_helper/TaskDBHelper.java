package com.example.snr.errand_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;


public class TaskDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "task_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_CREATIONTIME = "creation_time";
    private static final String COLUMN_DUETIME = "due_time";
    private static final String COLUMN_CREATORID = "creator_id";
    private static final String COLUMN_WORKERID = "woker_id";
    private static final String COLUMN_STATUS = "status";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table " + TABLE_NAME +" ("
            + COLUMN_ID + " integer primary key not null, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_TYPE + " text not null,"
            + COLUMN_DESC + " text not null,"
            + COLUMN_CREATIONTIME + " date," // not null, hasn't implemented yet
            + COLUMN_DUETIME + " date,"
            + COLUMN_CREATORID + " integer," // not null, hasn't implemented yet
            + COLUMN_WORKERID + " integer,"
            + COLUMN_STATUS + " text)"; // not null, hasn't implemented yet
            //+ " foreign key (" + COLUMN_CREATORID + ") references users)"; not implemented yet. note the parenthesis!!

    public TaskDBHelper(Context context){
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertTask(Task t) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String query = "select * from tasks";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        t.setTaskID(count);
        values.put(COLUMN_NAME, t.getName());
        values.put(COLUMN_DESC, t.getDesc());
        values.put(COLUMN_TYPE, t.getType());
//        values.put(COLUMN_STATUS, t.getStatus());
//        values.put(COLUMN_CREATORID, t.getCreator());
//        values.put(COLUMN_DUETIME, dateFormat.format(t.getDueTime()));
//        values.put(COLUMN_CREATIONTIME, dateFormat.format(t.getCreationTime()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor queryCursor() {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id,* from tasks", null);
        return c;
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID + " = " + Integer.toString(id),null);
    }

    /**
     * Code from user database for reference - Leifeng
     *
    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
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
    **/

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
