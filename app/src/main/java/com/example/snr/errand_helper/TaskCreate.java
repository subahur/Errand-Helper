package com.example.snr.errand_helper;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class TaskCreate extends AppCompatActivity {

    UserSessionManager session;
    DatabaseHelper helper = new DatabaseHelper(this);
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
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
        if(v.getId() == R.id.btn_submit_task){
            EditText taskName = (EditText)findViewById(R.id.et_task_name);
            EditText taskDesc = (EditText)findViewById(R.id.et_task_desc);
            Spinner taskType = (Spinner)findViewById(R.id.sp_task_type);

            String taskNameStr = taskName.getText().toString();
            String taskDescStr = taskDesc.getText().toString();
            String taskTypeStr = taskType.getSelectedItem().toString();

            HashMap<String, String> user = session.getUserInfo();
            String str = user.get(UserSessionManager.KEY_EMAIL);

            Task t = new Task();
            t.setName(taskNameStr);
            t.setDesc(taskDescStr);
            t.setType(taskTypeStr);
            t.setCreator(str);


            helper.insertTask(t);
            Toast.makeText(TaskCreate.this, "Task \""+t.getName()+"\" has been created", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btn_logout) {
            //logout and redirect to login oage which is main activity
            session.logoutUser();
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}

