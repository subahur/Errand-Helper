package com.example.snr.errand_helper;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;


public class TaskToDoCursorAdapter extends SimpleCursorAdapter {
    private DatabaseHelper helper;
    String email;

    public TaskToDoCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, DatabaseHelper helper, String email) {
        super(context, layout,c, from, to, flags);
        this.helper = helper;
        this.email = email;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        Button btn_delete_worker = (Button) v.findViewById(R.id.btn_delete_worker);
        Button btn_done = (Button) v.findViewById(R.id.btn_done);
        btn_delete_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = (Cursor) getItem(position);
                int id = cursor.getInt(cursor.getColumnIndex(helper.TASK_ID));
                helper.deleteWorker(id);
                cursor = helper.myToDoCursor(email);
                changeCursor(cursor);
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = (Cursor) getItem(position);
                int id = cursor.getInt(cursor.getColumnIndex(helper.TASK_ID));
                helper.doneWorker(id);
                cursor = helper.myToDoCursor(email);
                changeCursor(cursor);
            }
        });
        return v;
    }
}
