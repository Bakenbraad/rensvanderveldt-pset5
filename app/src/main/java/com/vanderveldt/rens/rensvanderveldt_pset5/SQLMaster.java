package com.vanderveldt.rens.rensvanderveldt_pset5;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLMaster extends SQLiteOpenHelper {

    // Table Name for master lists
    public static final String TABLE_NAME_MASTER = "MASTERLIST";

    // Table Name
    public static final String TABLE_NAME_ITEMS = "ITEMLIST";

    // Table columns
    public static final String _ID = "_id";
    public static final String MASTER_TITLE = "MasterName";
    public static final String ITEM_TITLE = "ItemName";
    public static final String MASTER_KEY = "ItemMaster";
    public static final String COMPLETED = "completed";



    // Database Information
    static final String DB_NAME = "TODO_DATABASE.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table querys
    private static final String CREATE_TABLE_MASTER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MASTER + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MASTER_TITLE + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEMS + "(" + MASTER_KEY + " INTEGER, " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_TITLE + " TEXT NOT NULL, " + COMPLETED + " INTEGER);";


    public SQLMaster(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create both tables
        db.execSQL(CREATE_TABLE_MASTER);
        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MASTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITEMS);
        onCreate(db);
    }
}