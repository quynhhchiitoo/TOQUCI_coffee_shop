package com.example.coffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the 'users' table with the specified columns
        String usersColumns[] = {"id", "fullname", "phone_number", "gmail", "address", "point"};
        String usersFeature_columns[] = {"INTEGER PRIMARY KEY AUTOINCREMENT", "TEXT", "TEXT", "TEXT", "TEXT", "INTEGER"};
        createTable(sqLiteDatabase, "users", usersColumns, usersFeature_columns, usersColumns.length);

        // Create the 'mycart' table with the specified columns
        String mycartColumns[] = new String[]{"id", "item_name", "item_price", "item_quantity", "is_spicy", "is_sauce", "cheese_quantity", "image"};
        String mycartFeatureColumns[] = new String[]{"INTEGER PRIMARY KEY AUTOINCREMENT", "TEXT", "INTEGER", "INTEGER", "BOOLEAN",
                "BOOLEAN", "INTEGER", "INTEGER"};
        createTable(sqLiteDatabase, "mycart", mycartColumns, mycartFeatureColumns, mycartColumns.length);

        // Create the 'history' table with the specified columns
        String historyColumns[] = {"order_day", "order_time", "item_name", "item_price", "item_quantity", "address"};
        String historyFeatureColumns[] = {"TEXT", "TEXT", "TEXT", "INTEGER", "INTEGER", "TEXT"};
        createTable(sqLiteDatabase, "history", historyColumns, historyFeatureColumns, historyColumns.length);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createTable(SQLiteDatabase sqLiteDatabase, String tableName,
                            String column[], String feature_columns[], int size){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
        for (int i = 0; i < size - 1; i++)
            createTableQuery += column[i] + " " + feature_columns[i] + ", ";
        createTableQuery += column[size - 1] + " " + feature_columns[size - 1] + "); ";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    public void clear(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
        db.close();
    }

    public long insertItem(MyCart item) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if an item with the same features already exists in the database
        String selection = "item_name=? AND item_price=? AND is_spicy=? AND is_sauce=? AND cheese_quantity=?";
        String[] selectionArgs = {
                item.getName(),
                String.valueOf(item.getPrice()),
                item.isSpicy() ? "1" : "0",
                item.isSauce() ? "1" : "0",
                String.valueOf(item.getCheeseQuantity())
        };

        Cursor cursor = db.rawQuery("SELECT * FROM mycart WHERE " + selection, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            // If an item with the same features exists, update its quantity
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int existingQuantity = cursor.getInt(cursor.getColumnIndex("item_quantity"));
            int newQuantity = existingQuantity + item.getQuantity(); // Increase the quantity by 1

            ContentValues values = new ContentValues();
            values.put("item_quantity", newQuantity);

            int updatedRows = db.update("mycart", values, "id=?", new String[]{String.valueOf(id)});
            cursor.close();
            db.close();

            if (updatedRows > 0) {
                // Return the ID of the existing item that was updated
                return id;
            } else {
                // Something went wrong with updating the item
                return -1;
            }
        } else {
            // If the item doesn't exist, insert it as a new entry in the database
            ContentValues values = new ContentValues();
            values.put("item_name", item.getName());
            values.put("item_price", item.getPrice());
            values.put("item_quantity", item.getQuantity()); // Set the quantity to 1 for a new item
            values.put("is_spicy", item.isSpicy());
            values.put("is_sauce", item.isSauce());
            values.put("cheese_quantity", item.getCheeseQuantity());
            values.put("image", item.getImage());

            long insertedId = db.insert("mycart", null, values);
            if (cursor != null)
                cursor.close();
            db.close();

            return insertedId;
        }
    }

    public int existItem(MyCart item){
        SQLiteDatabase db = this.getReadableDatabase();

        // Check if an item with the same features already exists in the database
        String selection = "item_name=? AND item_price=? AND is_spicy=? AND is_sauce=? AND cheese_quantity=?";
        String[] selectionArgs = {
                item.getName(),
                String.valueOf(item.getPrice()),
                item.isSpicy() ? "1" : "0",
                item.isSauce() ? "1" : "0",
                String.valueOf(item.getCheeseQuantity())
        };

        // Cursor cursor = db.rawQuery("SELECT * FROM mycart WHERE " + selection, selectionArgs);
        Cursor cursor = db.query("mycart", null, selection, selectionArgs, null, null, null);

        int existingQuantity = 0;
        if (cursor != null && cursor.moveToFirst()) {
            existingQuantity = cursor.getInt(cursor.getColumnIndex("item_quantity"));
            cursor.close();
        }
        db.close();
        return existingQuantity;
    }

    public boolean deleteItem(MyCart item) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the selection criteria
        String selection = "item_name=? AND item_price=? AND is_spicy=? AND is_sauce=? AND cheese_quantity=?";
        String[] selectionArgs = {
                item.getName(),
                String.valueOf(item.getPrice()),
                item.isSpicy() ? "1" : "0",
                item.isSauce() ? "1" : "0",
                String.valueOf(item.getCheeseQuantity())
        };

        int deletedRows = db.delete("mycart", selection, selectionArgs);
        db.close();

        if (deletedRows > 0)
            return true;
        return false;
    }





    public long insertUser(String fullname, String phoneNumber, String gmail, String address, int point) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("phone_number", phoneNumber);
        values.put("gmail", gmail);
        values.put("address", address);
        values.put("point", point);

        long insertedId = db.insert("users", null, values);
        db.close();

        return insertedId;
    }

    public void updatePointsForUser(String fullName, int newPoints) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("point", newPoints);

        int updatedRows = db.update("users", values, "fullname=?", new String[]{fullName});

        db.close();

        if (updatedRows > 0) {
            // Successfully updated the points for the user
            // You can add any additional handling here if needed
        } else {
            // Failed to update the points for the user
            // You can add any additional handling here if needed
        }
    }

    public boolean existUser(String fullname, String phone_number){
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
            int point = cursor.getInt(cursor.getColumnIndex("point"));

            user = new User(fullname, phoneNumber, gmail, address, point);
            cursor.close();
        }
        db.close();
        return user;
    }

    public ArrayList<MyCart> getAllItemsFromCart() {
        ArrayList<MyCart> items = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mycart", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String itemName = cursor.getString(cursor.getColumnIndex("item_name"));
                int itemPrice = cursor.getInt(cursor.getColumnIndex("item_price"));
                int itemQuantity = cursor.getInt(cursor.getColumnIndex("item_quantity"));
                boolean isSpicy = cursor.getInt(cursor.getColumnIndex("is_spicy")) == 1;
                boolean isSauce = cursor.getInt(cursor.getColumnIndex("is_sauce")) == 1;
                int cheeseQuantity = cursor.getInt(cursor.getColumnIndex("cheese_quantity"));
                int imageResource = cursor.getInt(cursor.getColumnIndex("image"));

                // Create a new MyCart object and add it to the ArrayList
                MyCart item = new MyCart(itemName, itemPrice, itemQuantity, isSpicy, isSauce, cheeseQuantity, imageResource);
                items.add(item);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return items;
    }

    public ArrayList<Order> getAllOrdersFromHistory() {
        ArrayList<Order> items = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM history", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String day = cursor.getString(cursor.getColumnIndex("order_day"));
                String time = cursor.getString(cursor.getColumnIndex("order_time"));
                String name = cursor.getString(cursor.getColumnIndex("item_name"));
                int price = cursor.getInt(cursor.getColumnIndex("item_price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("item_quantity"));
                String address = cursor.getString(cursor.getColumnIndex("address"));

                // Create a new Order object and add it to the ArrayList
                Order item = new Order(day, time, name, address, price, quantity);
                items.add(item);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return items;
    }

    public long insertOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("order_day", order.getDay());
        values.put("order_time", order.getTime());
        values.put("item_name", order.getItem_name());
        values.put("item_price", order.getItem_price());
        values.put("item_quantity", order.getItem_quantity());
        values.put("address", order.getAddress());

        long insertedId = db.insert("history", null, values);
        db.close();

        return insertedId;
    }

    public ArrayList<Order> getOrdersByDayAndTime(String day) {
        ArrayList<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "order_day=?";
        String[] selectionArgs = {day};

        Cursor cursor = db.query("history", null, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String itemName = cursor.getString(cursor.getColumnIndex("item_name"));
                String time = cursor.getString(cursor.getColumnIndex("order_time"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                int itemPrice = cursor.getInt(cursor.getColumnIndex("item_price"));
                int itemQuantity = cursor.getInt(cursor.getColumnIndex("item_quantity"));

                // Create a new Order object and add it to the ArrayList
                Order order = new Order(day, time, itemName, address, itemPrice, itemQuantity);
                orders.add(order);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return orders;
    }


}