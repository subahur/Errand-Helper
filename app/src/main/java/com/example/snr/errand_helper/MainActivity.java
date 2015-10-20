package com.example.snr.errand_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //
    //
    //
    public void onButtonClick(View v){
        if(v.getId() == R.id.ButtonLogin)
        {
            //check if email and password are matching
            EditText a = (EditText)findViewById(R.id.TFemail);
            String str = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.TFpassword);
            String form_password = b.getText().toString();

            String db_password = helper.searchPass(str);

            //check if password from form matches the one in db
            if(form_password.equals(db_password)){
                //then go to the homepage
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                intent.putExtra("Username",str);
                startActivity(intent);
            }
            else{
                //display error pop up
                Toast error = Toast.makeText(MainActivity.this ,"Invalid Email and/or password ", Toast.LENGTH_SHORT);
                error.show();
            }



        }
        if(v.getId() == R.id.signUp)
        {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }
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
