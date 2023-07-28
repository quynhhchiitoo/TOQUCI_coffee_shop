package com.example.coffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "coffee_db";
    private static final int DATABASE_VERSION = 1;
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the 'users' table with the specified columns
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fullname TEXT, " +
                "phone_number TEXT, " +
                "gmail TEXT, " +
                "address TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long insertUser(String fullname, String phoneNumber, String gmail, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("phone_number", phoneNumber);
        values.put("gmail", gmail);
        values.put("address", address);

        long insertedId = db.insert("users", null, values);
        db.close();

        return insertedId;
    }

    public boolean exists(String fullname, String phone_number){
        String str[] = new String[2];
        str[0] = fullname;
        str[1] = phone_number;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where fullname=? and phone_number=?", str);
        boolean userExists = false;
        if (cursor != null && cursor.moveToFirst()) {
            userExists = true;
            cursor.close();
        }
        db.close();
        return userExists;
    }

    public void clear(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
        db.close();
    }

    public User getFirstUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from users order by id asc limit 1";

        Cursor cursor = db.rawQuery(query, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
            String gmail = cursor.getString(cursor.getColumnIndex("gmail"));
            String address = cursor.getString(cursor.getColumnIndex("address"));

            user = new User(fullname, phoneNumber, gmail, address);
            cursor.close();
        }
        db.close();
        return user;
    }
}
