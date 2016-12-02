package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChoreAdd extends AppCompatActivity {

    // Edittext initilized.
    EditText todoName;
    EditText description;

    long master_id;

    // Start a DB manager.
    TodoInstanceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_add);

        // Open connection.
        manager = TodoInstanceManager.getInstance();
        manager.setManager(this);

        Bundle extras = getIntent().getExtras();
        master_id = extras.getLong("master_id");

        // Get edittexts.
        todoName = (EditText) findViewById(R.id.todoED);
    }

    public void addChoreAct(View view) {

        // Get text from edittext upon button press
        String name = todoName.getText().toString();
        if (name.length() == 0){
            Toast.makeText(this, "Please enter a title!", Toast.LENGTH_SHORT).show();
        }
        else{
            manager.addItem(name, master_id);
            Toast.makeText(this, "Added to list of chores", Toast.LENGTH_SHORT).show();

            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
            finish();
        }
    }
}
