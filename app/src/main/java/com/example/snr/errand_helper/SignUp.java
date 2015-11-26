package com.example.snr.errand_helper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created on 10/19/15.
 */
public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    //

    public void onButtonClick(View v){

        if(v.getId() == R.id.btn_signup){
            EditText email = (EditText)findViewById(R.id.et_email);
            EditText password1 = (EditText)findViewById(R.id.et_password_1);
            EditText password2 = (EditText)findViewById(R.id.et_password_2);

            //convert all to string values
            String emailstr = email.getText().toString();
            String password1str = password1.getText().toString();
            String password2str = password2.getText().toString();

            if(!password1str.equals(password2str)){
                //display error pop up
                Toast check_password = Toast.makeText(SignUp.this ,"Passwords don't match", Toast.LENGTH_SHORT);
                check_password.show();

            }
            else{
                //create a new user and add in db
                User u = new User();
                u.setEmail(emailstr);
                u.setPassword(password1str);

                helper.insertUser(u);
                Toast signup_success = Toast.makeText(SignUp.this ,"Sign up successful", Toast.LENGTH_SHORT);
                signup_success.show();
            }
        }

    }

}
