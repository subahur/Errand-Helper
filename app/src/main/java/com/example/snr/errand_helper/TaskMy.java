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
    private TaskMyCursorAdapter cursorAdapter;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_my);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserInfo();
        String email = user.get(UserSessionManager.KEY_EMAIL);

        ListView ls = (ListView)findViewById(R.id.lv_tasks);
        String[] from = new String[]{"name","description","type","creation_time","creator_email","due_time","price","status","worker_email"};
        int[] to = new int[]{R.id.tv_task_name,R.id.tv_task_description, R.id.tv_task_type, R.id.tv_created_time, R.id.tv_task_creator, R.id.tv_due_date, R.id.tv_price_mytask, R.id.tv_status, R.id.tv_worker_email};
        cursorAdapter = new TaskMyCursorAdapter(this, R.layout.task_my_entry, helper.myTaskCursor(email), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER,helper, email);

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
        return MenuChoice(item);
    }
}
