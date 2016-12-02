package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TodoInstanceManager manager;
    Cursor masterList;
    Cursor itemList;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = TodoInstanceManager.getInstance();
        manager.setManager(this);

        masterList = manager.getMasterTitles();

        MasterCursorAdapter masterAdapter = new MasterCursorAdapter(getApplicationContext(), masterList);

        final ListView masterListView = (ListView) findViewById(R.id.masterList);

        masterListView.setAdapter(masterAdapter);
        masterAdapter.notifyDataSetChanged();
        onRetainNonConfigurationInstance();

        // Long click for deletion
        masterListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> p, View v, final int po, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int ii) {
                        Cursor cursor = (Cursor) p.getItemAtPosition(po);
                        // Get the state's capital from this listview_item_row in the database.
                        long ID = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                        manager.deleteMaster(ID);
                        masterListView.invalidateViews();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int ii) {
                                dialog.dismiss();
                            }
                        }
                );
                builder.show();
                return true;
            }
        });
        // Regular click for master list selection
        masterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_row_item);

                String idstr = idTextView.getText().toString();
                id = Long.parseLong(idstr);

                itemList = manager.getMasterItems(id);

                ListView itemListView = (ListView) findViewById(R.id.itemList);
                TodoCursorAdapter itemAdapter = new TodoCursorAdapter(getApplicationContext(), itemList);
                itemAdapter.notifyDataSetChanged();

                itemListView.setAdapter(itemAdapter);
                itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                        TextView idTextView = (TextView) view.findViewById(R.id.id_row_item);
                        TextView todoTextView = (TextView) view.findViewById(R.id.title_row_item);

                        String item_id = idTextView.getText().toString();
                        String todo = todoTextView.getText().toString();

                        Intent goToEditTodo = new Intent(getApplicationContext(), EditTodo.class);
                        goToEditTodo.putExtra("todo", todo);
                        goToEditTodo.putExtra("id", item_id);
                        goToEditTodo.putExtra("master_id", String.valueOf(id));

                        startActivity(goToEditTodo);
                    }
                });
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Read values from the "savedInstanceState"
        long id_onRestore = savedInstanceState.getLong("id");
        itemList = manager.getMasterItems(id_onRestore);

        ListView itemListView = (ListView) findViewById(R.id.itemList);
        TodoCursorAdapter itemAdapter = new TodoCursorAdapter(getApplicationContext(), itemList);
        itemAdapter.notifyDataSetChanged();

        itemListView.setAdapter(itemAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_row_item);
                TextView todoTextView = (TextView) view.findViewById(R.id.title_row_item);

                String item_id = idTextView.getText().toString();
                String todo = todoTextView.getText().toString();

                Intent goToEditTodo = new Intent(getApplicationContext(), EditTodo.class);
                goToEditTodo.putExtra("todo", todo);
                goToEditTodo.putExtra("id", item_id);
                goToEditTodo.putExtra("master_id", String.valueOf(id));

                startActivity(goToEditTodo);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need into "outState"
        outState.putLong("id", id);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void addChore(View view) {
        Intent goToAddChore = new Intent(this, ChoreAdd.class);
        goToAddChore.putExtra("master_id", id);
        startActivity(goToAddChore);
    }

    public void addList(View view) {
        Intent goToAddMaster = new Intent(this, addMaster.class);
        startActivity(goToAddMaster);

    }
}
