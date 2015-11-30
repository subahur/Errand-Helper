package com.example.snr.errand_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "errandHelper22.db";

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_PHONE = "phone";

    public static final String[] ALL_KEYS = new String[] {USER_ID, USER_EMAIL, USER_PASSWORD, USER_PHONE};

    private static final String TASK_TABLE_NAME = "tasks";
    public static final String TASK_ID = "task_id"; // set public for deleting. just a test
    private static final String TASK_NAME = "name";
    private static final String TASK_TYPE = "type";
    private static final String TASK_DESC = "description";
    private static final String TASK_CREATIONTIME = "creation_time";
    private static final String TASK_DUETIME = "due_time";
    private static final String TASK_CREATOREMAIL = "creator_email";
    private static final String TASK_CREATORPHONE = "creator_phone";
    private static final String TASK_WORKEREMAIL = "worker_email";
    private static final String TASK_STATUS = "status";

    SQLiteDatabase db;

    private static final String CREATE_USER_TABLE = "create table " + USER_TABLE_NAME + " ("
            + USER_ID + " integer primary key not null,"
            + USER_EMAIL + " text not null,"
            + USER_PASSWORD + " text not null,"
            + USER_PHONE + " text not null)";

    private static final String CREATE_TASK_TABLE = "create table " + TASK_TABLE_NAME +" ("
            + TASK_ID + " integer primary key not null, "
            + TASK_NAME + " text not null,"
            + TASK_TYPE + " text not null,"
            + TASK_DESC + " text not null,"
            + TASK_CREATIONTIME + " text not null,"
            + TASK_DUETIME + " text not null,"
            + TASK_CREATOREMAIL + " text not null,"
            + TASK_CREATORPHONE + " text not null,"
            + TASK_WORKEREMAIL + " text,"
            + TASK_STATUS + " text)"; // not null, hasn't implemented yet
//            + " foreign key (" + USER_EMAIL + ") references users)";

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
        values.put(USER_PHONE, u.getPhone());
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
        values.put(TASK_CREATOREMAIL, t.getCreator());
        values.put(TASK_DUETIME, t.getDueTime());
        values.put(TASK_CREATIONTIME, t.getCreationTime());
        values.put(TASK_CREATORPHONE, t.getCreatorPhone());
        values.put(TASK_STATUS, t.getStatus());

        db.insert(TASK_TABLE_NAME, null, values);
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE_NAME, TASK_ID + " = " + Integer.toString(id),null);
    }

    public void deleteWorker(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_WORKEREMAIL, "");
        values.put(TASK_STATUS, "available");
        db.update(TASK_TABLE_NAME, values, TASK_ID + " = '" + Integer.toString(id) + "'", null);
    }

    public void updateWorker(String email, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_WORKEREMAIL, email);
        values.put(TASK_STATUS, "picked");
//        values.put(TASK_ID, Integer.toString(id));
        db.update(TASK_TABLE_NAME, values, TASK_ID + " = '" + Integer.toString(id) + "'", null);
    }

    public Cursor taskQueryCursor() {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id,* from tasks", null);
        return c;
    }

    // return tasks created by user with this email
    public Cursor myTaskCursor (String email) {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id, * from tasks where creator_email = \'"+ email + "\'",null);
        return c;
    }

    public Cursor myToDoCursor (String email) {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id, * from tasks where worker_email = '" + email + "'", null);
        return c;
    }

    // return tasks created by users other than with this email
    public Cursor otherTaskCursor (String email) {
        Cursor c = getReadableDatabase().rawQuery("select rowid _id, * from tasks where creator_email != '" + email + "' and status = 'available'", null);
        return c;
    }



    public Cursor getPhoneNumber(String email){
        //String where = KEY_DESCRIPTION + "=" + description;
        Cursor c = getReadableDatabase().rawQuery("select phone, * from users where email = \'"+ email + "\'",null);
        //if (c != null) {
         //   c.moveToFirst();
        //}
        return c;
    }

    public Cursor emailExistsOrNot(String email){
        //String where = KEY_DESCRIPTION + "=" + description;
        Cursor c = getReadableDatabase().rawQuery("select email, * from users where email = \'"+ email + "\'",null);
        //if (c != null) {
        //   c.moveToFirst();
        //}
        return c;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS "+ USER_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
