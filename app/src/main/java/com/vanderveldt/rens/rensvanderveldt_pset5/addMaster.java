package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addMaster extends AppCompatActivity {

    EditText editText;
    TodoInstanceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_master);
        manager = TodoInstanceManager.getInstance();

        EditText editText = (EditText) findViewById(R.id.masterTitleED);
    }


    public void saveMaster(View view) {
        String title = editText.getText().toString();
        manager.addMaster(title);
        finish();
    }
}