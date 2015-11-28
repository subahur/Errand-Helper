package com.example.snr.errand_helper;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class TaskSelectCursorAdapter extends SimpleCursorAdapter {
    private DatabaseHelper helper;
    String email;

    public TaskSelectCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, DatabaseHelper helper, String email) {
        super(context, layout,c, from, to, flags);
        this.helper = helper;
        this.email = email;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        Button btn_select = (Button) v.findViewById(R.id.btn_select_task);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = (Cursor) getItem(position);
                int id = cursor.getInt(cursor.getColumnIndex(helper.TASK_ID));
                helper.updateWorker(email, id);
                cursor = helper.otherTaskCursor(email);
                changeCursor(cursor);
            }
        });
        return v;
    }

}
