package com.vanderveldt.rens.rensvanderveldt_pset5;

/**
 * Created by Rens on 30-11-2016.
 */
public class TodoInstanceManager {
    private static TodoInstanceManager ourInstance = new TodoInstanceManager();

    public static TodoInstanceManager getInstance() {
        return ourInstance;
    }

    private TodoInstanceManager() {
    }
}
