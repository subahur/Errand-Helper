package com.example.snr.errand_helper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TaskCreate extends AppCompatActivity {

    UserSessionManager session;
    DatabaseHelper helper = new DatabaseHelper(this);
    String currentUserEmail;
    private static Calendar time;
    private static TextView dateTextView;
    private static TextView timeTextView;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");;
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static class DatePickerFragment extends android.app.DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            time.set(year,month,day);
            TaskCreate.updateDate();
        }
    }

    public static class TimePickerFragment extends android.app.DialogFragment
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
            time.set(Calendar.HOUR_OF_DAY,hourOfDay);
            time.set(Calendar.MINUTE, minute);
            TaskCreate.updateTime();
        }
    }

    public static void updateDate() {
        dateTextView.setText(dateFormat.format(time.getTime()));
    }

    public static void updateTime() {
        timeTextView.setText(timeFormat.format(time.getTime()));
    }

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        session = new UserSessionManager(getApplicationContext());
        dateTextView = (TextView) this.findViewById(R.id.dateString);
        timeTextView = (TextView) this.findViewById(R.id.timeString);
        time = Calendar.getInstance();
        String dateString = dateFormat.format(time.getTime());
        String timeString = timeFormat.format(time.getTime());
        dateTextView.setText(dateString);
        timeTextView.setText(timeString);

        HashMap<String, String> user = session.getUserInfo();
        currentUserEmail = user.get(UserSessionManager.KEY_EMAIL);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.btn_submit_task){
            EditText taskName = (EditText)findViewById(R.id.et_task_name);
            EditText taskDesc = (EditText)findViewById(R.id.et_task_desc);
            EditText price = (EditText)findViewById(R.id.et_price);
            Spinner taskType = (Spinner)findViewById(R.id.sp_task_type);

            String taskNameStr = taskName.getText().toString();
            String taskDescStr = taskDesc.getText().toString();
            String taskTypeStr = taskType.getSelectedItem().toString();
            String priceStr = price.getText().toString();

            HashMap<String, String> user = session.getUserInfo();
            String str = user.get(UserSessionManager.KEY_EMAIL);

            Cursor c = helper.getPhoneNumber(str);
            c.moveToFirst();
            String phoneNumber = c.getString(0);

            Task t = new Task();
            t.setName(taskNameStr);
            t.setDesc(taskDescStr);
            t.setType(taskTypeStr);
            t.setCreator(str);
            t.setStatus("Available");
            t.setCreatorPhone(phoneNumber);
            t.setPrice(priceStr);
            t.setDueTime(dateFormat.format(time.getTime()) + timeFormat.format(time.getTime()));

            helper.insertTask(t);
            //Toast.makeText(TaskCreate.this, "Task \""+t.getName()+"\" has been created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,HomePage.class);
            intent.putExtra("Username", currentUserEmail);
            session.createSession(currentUserEmail);
            startActivity(intent);
        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
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

