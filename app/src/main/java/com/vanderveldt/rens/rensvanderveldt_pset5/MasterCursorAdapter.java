package com.vanderveldt.rens.rensvanderveldt_pset5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Rens on 2-12-2016.
 */

public class MasterCursorAdapter extends CursorAdapter {
    public MasterCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.simple_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvTitle = (TextView) view.findViewById(R.id.title_row_item);
        TextView tvID = (TextView) view.findViewById(R.id.id_row_item);

        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("MasterName"));
        long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));

        // Populate fields with extracted properties
        tvTitle.setText(title);

        tvID.setText(String.valueOf(id));
    }
}
