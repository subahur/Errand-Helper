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

        String[] from = new String[]{"name","description","type","creation_time","creator_id","due_time"};
        int[] to = new int[]{R.id.tv_task_name,R.id.tv_task_description, R.id.tv_task_type, R.id.tv_created_time, R.id.tv_task_creator,R.id.tv_due_date};
        cursorAdapter = new ItemCursorAdapter(this, R.layout.task_entry, helper.taskQueryCursor(), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER,helper);

        ls.setAdapter(cursorAdapter);

        session = new UserSessionManager(getApplicationContext());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }

    //creates menu option
    private void CreateMenu(Menu menu){
        menu.setQwertyMode(true);
        MenuItem mu1 = menu.add(0,0,0,"Logout");//send item id to 0 if Add isclicked
        {
            mu1.setAlphabeticShortcut('a');
            //mu1.setIcon(R.drawable.ic_launcher);
        }
    }

    //handles events when options menu is clicked
    private boolean MenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case 0://if add is pressed
                session.logoutUser();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //    return true;
        //}

        // return super.onOptionsItemSelected(item);
        return MenuChoice(item);
    }
}
