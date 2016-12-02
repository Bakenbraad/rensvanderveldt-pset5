package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {

    private long _id;
    private long master_id;

    private TodoInstanceManager manager;

    EditText todoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Edit Chore");
        setContentView(R.layout.activity_edit_todo);
        manager = TodoInstanceManager.getInstance();
        manager.setManager(this);


        todoText = (EditText) findViewById(R.id.item_name);

        Bundle extras = getIntent().getExtras();
        // Get extras from intent
        String idstr = extras.getString("id");
        String name = extras.getString("todo");
        String master_idstr = extras.getString("master_id");

        // Turn id into long id
        _id = Long.parseLong(idstr);
        master_id = Long.parseLong(master_idstr);

        // Show user which to-do they are editing
        todoText.setText(name);
    }

    // Back function
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("id", master_id);
        startActivity(home_intent);
        finish();
    }

    // When update is pressed
    public void updateChore(View view) {
        String title = todoText.getText().toString();
        if (title.length() == 0){
            Toast.makeText(this, "Please enter a To-Do", Toast.LENGTH_SHORT).show();
        }
        else{
            manager.updateItem(_id, title);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            this.returnHome();
        }
    }

    public void deleteChore(View view) {
        manager.deleteItem(_id);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        this.returnHome();
    }

    public void completeChore(View view) {
        manager.completeItem(_id);
        Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show();
        this.returnHome();
    }
}
