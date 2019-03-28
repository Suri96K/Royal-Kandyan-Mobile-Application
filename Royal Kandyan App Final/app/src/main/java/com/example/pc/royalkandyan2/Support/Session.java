package com.example.pc.royalkandyan2.Support;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Session {

    // LogCat tag
    private static String TAG = "SESSION";

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "RoyalKandyan";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USER_TYPE = "userType";

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String userType) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_USER_TYPE, userType);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getUserType(){
        return pref.getString(KEY_USER_TYPE, "NO" );
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
