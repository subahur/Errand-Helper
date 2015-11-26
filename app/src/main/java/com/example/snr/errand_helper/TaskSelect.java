package com.example.snr.errand_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TaskSelect extends AppCompatActivity {

    UserSessionManager session;
    ArrayList<Integer> taskList = new ArrayList<Integer>();
    private ItemCursorAdapter cursorAdapter;
    DatabaseHelper helper = new DatabaseHelper(this);

    Task testTask = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_select);
        ListView ls = (ListView)findViewById(R.id.lv_tasks);

//        testTask.setName("test");
//        testTask.setDesc("this is a test");
//        testTask.setType("Ride");
//        helper.insertTask(testTask);

        String[] from = new String[]{"name","description","type"};
        int[] to = new int[]{R.id.tv_task_name,R.id.tv_task_description, R.id.tv_task_type};
        cursorAdapter = new ItemCursorAdapter(this, R.layout.task_entry, helper.taskQueryCursor(), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER,helper);

        ls.setAdapter(cursorAdapter);

        session = new UserSessionManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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


    public void onButtonClick(View v) {
        if (v.getId() == R.id.btn_logout) {
            //logout and redirect to login oage which is main activity
            session.logoutUser();
        }
    }
}
