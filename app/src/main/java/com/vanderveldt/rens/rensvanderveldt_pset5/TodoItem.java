package com.vanderveldt.rens.rensvanderveldt_pset5;

/**
 * Created by Rens on 27-11-2016.
 */

public class TodoItem {

    private boolean completed = false;
    private String title;
    private String description;

    public TodoItem(String itemtitle, String itemdescription) {
        setTitle(itemtitle);
        setDescription(itemdescription);
    }

    public void setTitle(String titleset){
        title = titleset;
    }

    public void markAsDoneTodoItem(){
        if(!completed){
            completed = true;
        }
    }

    public void setDescription(String desc){
        description = desc;
    }


}
