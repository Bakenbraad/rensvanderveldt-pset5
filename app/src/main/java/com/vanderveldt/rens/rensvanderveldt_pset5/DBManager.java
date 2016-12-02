package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


// Create Read Update and Delete operations are preformed here.

public class DBManager {

    private SQLMaster dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new SQLMaster(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Create

    public void insertmaster(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLMaster.MASTER_TITLE, name);
        database.insert(SQLMaster.TABLE_NAME_MASTER, null, contentValue);
    }

    public void insertItem(String name, long masterID) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLMaster.ITEM_TITLE, name);
        contentValue.put(SQLMaster.MASTER_KEY, masterID);
        database.insert(SQLMaster.TABLE_NAME_ITEMS, null, contentValue);
    }

    // Read

    public Cursor fetchMaster() {
        String[] columns = new String[] { SQLMaster._ID, SQLMaster.MASTER_TITLE};
        Cursor cursor = database.query(SQLMaster.TABLE_NAME_MASTER, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchItemFromMaster(long masterID) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + SQLMaster.TABLE_NAME_ITEMS + " WHERE " + SQLMaster.MASTER_KEY + " = ?", new String[] {String.valueOf(masterID)});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Update

    public int updateItem(long _id, String itemTitle, long masterID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLMaster.ITEM_TITLE, itemTitle);
        contentValues.put(SQLMaster.MASTER_KEY, masterID);
        int i = database.update(SQLMaster.TABLE_NAME_ITEMS, contentValues, SQLMaster._ID + " = " + _id, null);
        return i;
    }

    public int updateMaster(long _id, String masterTitle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLMaster.MASTER_TITLE, masterTitle);
        int i = database.update(SQLMaster.TABLE_NAME_MASTER, contentValues, SQLMaster._ID + " = " + _id, null);
        return i;
    }

    // Delete

    public void deleteMaster(long _id) {
        database.delete(SQLMaster.TABLE_NAME_MASTER, SQLMaster._ID + "=" + _id, null);
        database.delete(SQLMaster.TABLE_NAME_ITEMS, SQLMaster.MASTER_KEY + "=" + _id, null);
    }
    public void deleteItem(long _id) {
        database.delete(SQLMaster.TABLE_NAME_ITEMS, SQLMaster._ID + "=" + _id, null);
    }

}