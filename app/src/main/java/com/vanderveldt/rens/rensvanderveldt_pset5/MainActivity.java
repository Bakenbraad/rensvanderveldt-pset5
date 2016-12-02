package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TodoInstanceManager manager;
    String[] masterList;
    String[] itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = TodoInstanceManager.getInstance();
        manager.setManager(this);

        manager.addMaster("test");
        masterList = manager.getMasterTitles();

        ArrayAdapter masteradapter = new ArrayAdapter(getApplicationContext(), R.layout.simple_master_item, masterList);

        ListView masterListView = (ListView) findViewById(R.id.masterList);

        masterListView.setAdapter(masteradapter);
        masterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_row);

                String idstr = idTextView.getText().toString();
                Integer id = Integer.parseInt(idstr);

                String[] itemList = manager.getMasterItems(id);

                ListView itemListView = (ListView) findViewById(R.id.itemList);
                ArrayAdapter itemadapter = new ArrayAdapter(getApplicationContext(), R.layout.simple_item, itemList);

                itemListView.setAdapter(itemadapter);
                itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                        TextView idTextView = (TextView) view.findViewById(R.id.id_row_item);
                        TextView todoTextView = (TextView) view.findViewById(R.id.title_row_item);

                        String id = idTextView.getText().toString();
                        String todo = todoTextView.getText().toString();

                        Intent goToEditTodo = new Intent(getApplicationContext(), EditTodo.class);
                        goToEditTodo.putExtra("todo", todo);
                        goToEditTodo.putExtra("id", id);

                        startActivity(goToEditTodo);
                    }
                });
            }
        });
    }

    public void addChore(View view) {
    }

    public void addList(View view) {
        Intent goToAddMaster = new Intent(this, addMaster.class);
        startActivity(goToAddMaster);

    }
}
