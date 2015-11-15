package com.example.snr.errand_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TaskNew extends AppCompatActivity {

    UserSessionManager session;
    TaskDBHelper helper = new TaskDBHelper(this);
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_new);
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

    public void onButtonClick(View v){
        //for now just create a pop up, later will create a entry in the table tasks
        if(v.getId() == R.id.ButtonSubmitTask){
            EditText taskName = (EditText)findViewById(R.id.TFTaskName);
            EditText taskDesc = (EditText)findViewById(R.id.TFTaskDescription);
            Spinner taskType = (Spinner)findViewById(R.id.SPTaskTypes);

            String taskNameStr = taskName.toString();
            String taskDescStr = taskDesc.toString();
            String taskTypeStr = taskType.getSelectedItem().toString();

            Task t = new Task();
            t.setName(taskNameStr);
            t.setDesc(taskDescStr);
            t.setType(taskTypeStr);

            helper.insertTask(t);

            Toast task_submit_success = Toast.makeText(TaskNew.this ,"Task is successfully created", Toast.LENGTH_SHORT);
            task_submit_success.show();
        }
        if (v.getId() == R.id.BLogout) {
            //logout and redirect to login oage which is main activity
            session.logoutUser();
        }
    }
}

