package com.example.snr.errand_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.HashMap;

public class TaskMy extends AppCompatActivity {

    UserSessionManager session;
    private TaskEditCursorAdapter cursorAdapter;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_my);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserInfo();
        String email = user.get(UserSessionManager.KEY_EMAIL);

        ListView ls = (ListView)findViewById(R.id.lv_tasks);
        String[] from = new String[]{"name","description","type","creation_time","creator_email"};
        int[] to = new int[]{R.id.tv_task_name,R.id.tv_task_description, R.id.tv_task_type, R.id.tv_created_time, R.id.tv_task_creator};
        cursorAdapter = new TaskEditCursorAdapter(this, R.layout.task_edit_entry, helper.myTaskCursor(email), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER,helper);

        ls.setAdapter(cursorAdapter);

        session = new UserSessionManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
