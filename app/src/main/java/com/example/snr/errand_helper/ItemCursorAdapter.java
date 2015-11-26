package com.example.snr.errand_helper;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class ItemCursorAdapter extends SimpleCursorAdapter{

    private TaskDBHelper helper;

    public ItemCursorAdapter (Context context, int layout, Cursor c, String[] from, int[] to, int flags, TaskDBHelper helper) {
        super(context, layout,c, from, to, flags);
        this.helper = helper;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        helper.close();
//        Button btn_del = (Button) v.findViewById(R.id.btn_delete);
//        btn_del.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Cursor cursor = (Cursor) getItem(position);
//                int id = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_ID));
//                helper.deleteItem(id);
//                cursor = helper.queryCursor();
//                changeCursor(cursor);
//            }
//        });
        return v;
    }



}
