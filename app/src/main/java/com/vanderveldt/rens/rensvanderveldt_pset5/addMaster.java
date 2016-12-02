package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addMaster extends AppCompatActivity {

    EditText editText;
    TodoInstanceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add a list");
        setContentView(R.layout.activity_add_master);
        manager = TodoInstanceManager.getInstance();
        manager.setManager(this);

        editText = (EditText) findViewById(R.id.masterTitleED);
    }


    public void saveMaster(View view) {
        String title = editText.getText().toString();
        if (title.length() != 0){
            manager.addMaster(title);
            Intent goHome = new Intent(this, MainActivity.class);
            startActivity(goHome);
        }
        else{
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }

    }
}
