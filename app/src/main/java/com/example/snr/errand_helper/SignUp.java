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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onSignUpClick(View v){

        if(v.getId() == R.id.signUp){
            EditText username = (EditText)findViewById(R.id.username);
            EditText password1 = (EditText)findViewById(R.id.password1);
            EditText password2 = (EditText)findViewById(R.id.password2);

            //convert all to string values
            String usernamestr = username.getText().toString();
            String password1str = password1.getText().toString();
            String password2str = password2.getText().toString();

            if(!password1str.equals(password2str)){
                //display error pop up
                Toast check_password = Toast.makeText(SignUp.this ,"Passwords don't match", Toast.LENGTH_SHORT);
                check_password.show();
            }
        }

    }

}
