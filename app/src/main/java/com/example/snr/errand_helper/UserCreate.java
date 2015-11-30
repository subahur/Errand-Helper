package com.example.snr.errand_helper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 10/19/15.
 */
public class UserCreate extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
    }

    //

    public void onButtonClick(View v){

        if(v.getId() == R.id.btn_signup){
            EditText email = (EditText)findViewById(R.id.et_email);
            EditText password1 = (EditText)findViewById(R.id.et_password_1);
            EditText password2 = (EditText)findViewById(R.id.et_password_2);
            EditText phone = (EditText)findViewById(R.id.et_phone);

            //convert all to string values
            String emailstr = email.getText().toString();
            String password1str = password1.getText().toString();
            String password2str = password2.getText().toString();
            String phonestr = phone.getText().toString();

            Cursor c = helper.emailExistsOrNot(emailstr);
            int emailCount = c.getCount();

            if(!isValid(emailstr)){
                Toast check_email = Toast.makeText(UserCreate.this ,"Email is not valid, has to be a 'example@example.com' expression", Toast.LENGTH_SHORT);
                check_email.show();
            }
            else if(emailCount>0){
                Toast check_email_uniqueness = Toast.makeText(UserCreate.this ,"Email already exists,please enter a unique email", Toast.LENGTH_SHORT);
                check_email_uniqueness.show();
            }
            else if(!password1str.equals(password2str)){
                //display error pop up
                Toast check_password = Toast.makeText(UserCreate.this ,"Password and Re-type password doesn't match", Toast.LENGTH_SHORT);
                check_password.show();

            }
            else{
                //create a new user and add in db
                User u = new User();
                u.setEmail(emailstr);
                u.setPassword(password1str);
                u.setPhone(phonestr);
                helper.insertUser(u);
                Toast signup_success = Toast.makeText(UserCreate.this ,"Sign up successful", Toast.LENGTH_SHORT);
                signup_success.show();
                UserSessionManager session = new UserSessionManager(getApplicationContext());
                session.createSession(emailstr);
                Intent intent = new Intent(this,HomePage.class);
                intent.putExtra("Username",emailstr);
                startActivity(intent);
            }
        }

    }

    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }

}
