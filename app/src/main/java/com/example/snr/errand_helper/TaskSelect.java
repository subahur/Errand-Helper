package com.example.snr.errand_helper;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class TaskSelect extends AppCompatActivity {

    UserSessionManager session;
    private TaskSelectCursorAdapter cursorAdapter;
    DatabaseHelper helper = new DatabaseHelper(this);
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_select);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserInfo();
        String email = user.get(UserSessionManager.KEY_EMAIL);


        ListView ls = (ListView)findViewById(R.id.lv_tasks);
        String[] from = new String[]{"name","description","type","creation_time","creator_email","due_time","creator_phone"};
        int[] to = new int[]{R.id.tv_task_name,R.id.tv_task_description, R.id.tv_task_type, R.id.tv_created_time, R.id.tv_task_creator, R.id.tv_due_date, R.id.tv_phone_number};
        cursorAdapter = new TaskSelectCursorAdapter(this, R.layout.task_select_entry, helper.otherTaskCursor(email), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER,helper, email);

        ls.setAdapter(cursorAdapter);


    }


    public void onCallClick(View v){

        if(v.getId() == R.id.btnPhone){
            RelativeLayout single = (RelativeLayout) v.getParent();
            TextView phoneNumber = (TextView)single.findViewById(R.id.tv_phone_number);
            String phoneStr = (String) phoneNumber.getText();
            Button b = (Button) single.findViewById(R.id.btnPhone);
            //Toast.makeText(this, "no "+phoneStr, Toast.LENGTH_LONG).show();
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialContactPhone("phoneStr");
                }
            });
            //Cursor c = myDb.getPrimaryKey(heading, description);
            //myDb.deleteRow(c.getLong(0));
            //populateLVFromDatabase();
        }

    }

    private void dialContactPhone(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
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
