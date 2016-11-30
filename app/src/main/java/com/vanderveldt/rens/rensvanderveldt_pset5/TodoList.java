package com.vanderveldt.rens.rensvanderveldt_pset5;

/**
 * Created by Rens on 27-11-2016.
 */

public class TodoList {

    private String mastertitle;

    public TodoList(String initMasterTitle){
        setMastertitle(initMasterTitle);
        // Add to sql database
    }

    public void setMastertitle(String setMasterTitle){
        mastertitle = setMasterTitle;
    }

    public void addItem(String itemtitle, String itemdescription){
        TodoItem newItem = new TodoItem(itemtitle, itemdescription);
        newItem.setDescription(itemdescription);
        newItem.setTitle(itemtitle);
        // Add item to sql database
    }

    public void removeItem(long _id){
        // remove item from sql database
    }

    public void removeList(long masterid){

    }
}
