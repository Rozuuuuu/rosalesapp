package com.example.rosaleslloyd;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rosaleslloyd.model.User;
import com.google.gson.Gson;

public class UserPrefManager {

    private static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_USER = "key_user";

    private static UserPrefManager mInstance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private UserPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized UserPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserPrefManager(context);
        }
        return mInstance;
    }

    public void saveUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    public User getUser() {
        String json = sharedPreferences.getString(KEY_USER, null);
        return new Gson().fromJson(json, User.class);
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }
}