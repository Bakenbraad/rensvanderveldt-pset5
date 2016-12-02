package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Rens on 30-11-2016.
 */
public class TodoInstanceManager {
    private static TodoInstanceManager ourInstance = new TodoInstanceManager();


    Context c;
    DBManager dbManager;

    public static TodoInstanceManager getInstance() {
        return ourInstance;
    }

    private TodoInstanceManager() {
    }

    public Cursor getMasterTitles(){
        return dbManager.fetchMaster();
    }

    public Cursor getMasterItems(long masterID) {
        return dbManager.fetchItemFromMaster(masterID);
    }

    public void addMaster(String name){
        dbManager.insertmaster(name);
    }

    public void addItem(String name, long master) { dbManager.insertItem(name, master); }

    public void setManager(Context c) {
        this.c = c;
        dbManager = new DBManager(c);
        dbManager.open();
    }
    public void closeManager(){
        dbManager.close();
    }

    public void updateItem(long _id, String title){
        dbManager.updateItem(_id, title);
    }

    public void deleteItem(long _id){
        dbManager.deleteItem(_id);
    }

    public void deleteMaster(long _id){
        dbManager.deleteMaster(_id);
    }

    public void completeItem(long _id) {
        dbManager.completeItem(_id);
    }

}
