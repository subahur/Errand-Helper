package com.example.snr.errand_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    public static Button button_new_task_submit;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        session = new UserSessionManager(getApplicationContext());

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
        onClickNewTaskButtonListner();
        onClickSelectTaskButtonListner();

    }

    public void onClickNewTaskButtonListner() {
        button_new_task_submit = (Button) findViewById(R.id.BTasker);
        button_new_task_submit.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View w) {
                        Intent intent = new Intent("com.example.snr.errand_helper.TaskCreate");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickSelectTaskButtonListner() {
        button_new_task_submit = (Button) findViewById(R.id.BWorker);
        button_new_task_submit.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View w) {
                        Intent intent = new Intent("com.example.snr.errand_helper.TaskSelect");
                        startActivity(intent);
                    }
                }
        );
    }

    public void logOut(View v) {
        //if (v.getId() == R.id.BLogout) {
            //logout and redirect to login oage which is main activity
            session.logoutUser();
        //}
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
}
