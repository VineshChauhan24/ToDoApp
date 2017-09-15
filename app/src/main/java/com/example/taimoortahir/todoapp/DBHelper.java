package com.example.taimoortahir.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taimoor Tahir on 23-Jul-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ToDoList_DB";

    // Contacts table name
    private static final String TABLE_NAME = "tasks";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_F_NAME = "firstname";
    private static final String KEY_L_NAME = "lastname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TASK = "task";
    private static final String KEY_DATE = "date";
    String password;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_F_NAME + " TEXT,"
                + KEY_L_NAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASSWORD +" TEXT,"
                + KEY_TASK + " TEXT," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    //code to get the register
    String getregister(String username){
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.query(TABLE_NAME, new String[] {
                        KEY_PASSWORD }, KEY_EMAIL + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);


//        Cursor cursor =   db.rawQuery("select * from "+TABLE_NAME+ " where email = "+username+"" , null);

//        Cursor cursor=db.query(TABLE_NAME,null,"email_id=?",new String[]{username},null, null, null, null);

        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            int columnIndex = cursor.getColumnIndex(KEY_PASSWORD);

            password = cursor.getString(columnIndex);
            cursor.close();
        }
        return password;
    }

    // Adding new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_F_NAME, task.getFirstname());
        values.put(KEY_L_NAME, task.getLastname());
        values.put(KEY_EMAIL, task.getEmail());
        values.put(KEY_PASSWORD, task.getPassword());
        values.put(KEY_TASK, task.getTask());
        values.put(KEY_DATE, task.getDate());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Getting single task
    public Task getTasks(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_F_NAME, KEY_L_NAME, KEY_EMAIL, KEY_PASSWORD,
                        KEY_TASK, KEY_DATE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6));

        return task;
    }

    // Getting All Tasks
    public List<Task> getAllTask() {
        List<Task> contactList = new ArrayList<Task>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setFirstname(cursor.getString(1));
                task.setLastname(cursor.getString(2));
                task.setEmail(cursor.getString(3));
                task.setPassword(cursor.getString(4));
                task.setTask(cursor.getString(5));
                task.setDate(cursor.getString(6));

                contactList.add(task);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    // Getting Tasks Count
    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single Task
    public int updateTasks(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_F_NAME, task.getFirstname());
        values.put(KEY_L_NAME, task.getLastname());
        values.put(KEY_EMAIL, task.getEmail());
        values.put(KEY_PASSWORD, task.getPassword());
        values.put(KEY_TASK, task.getTask());
        values.put(KEY_DATE, task.getDate());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    // Deleting single Task
    public void deleteTasksTable(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
        db.close();
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getTableContacts() {
        return TABLE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
