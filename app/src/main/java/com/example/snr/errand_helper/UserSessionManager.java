package com.example.snr.errand_helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by on 10/20/15.
 */
public class UserSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context ;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "session";
    private static final String IS_LOGIN = "isLogin";
    public static final String KEY_EMAIL = "email";

    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createSession(String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public HashMap<String, String> getUserInfo(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        //redirect to login page(main activity)
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

}
