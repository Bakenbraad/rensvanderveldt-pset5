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

    String [] MasterTitles = {""};
    String [] MasterItems;

    public static TodoInstanceManager getInstance() {
        return ourInstance;
    }

    private TodoInstanceManager() {


    }

    public String[] getMasterTitles(){
        Cursor masters = dbManager.fetchMaster();

        for(int i = 0; i < masters.getCount(); i ++){
            MasterTitles[i] = masters.getString(i);
        }
        masters.close();
        return MasterTitles;
    }

    public String[] getMasterItems(int masterID) {
        Cursor items = dbManager.fetchItemFromMaster(masterID);
        for(int i = 0; i < items.getCount(); i ++){
            MasterItems[i] = items.getString(i);
        }
        items.close();
        return MasterItems;
    }

    public void addMaster(String name){
        dbManager.insertmaster(name);
    }

    public void setManager(Context c) {
        this.c = c;
        DBManager dbManager = new DBManager(get);
    }

}
