package com.example.rosaleslloyd.Class;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rosaleslloyd.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserPrefManager {

    private static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_USERS = "key_users";

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
        String json = sharedPreferences.getString(KEY_USERS, null);
        List<User> userList;

        if (json != null) {
            Type type = new TypeToken<List<User>>() {}.getType();
            userList = gson.fromJson(json, type);
        } else {
            userList = new ArrayList<>();
        }

        userList.add(user);
        String updatedJson = gson.toJson(userList);
        editor.putString(KEY_USERS, updatedJson);
        editor.apply();
    }

    public List<User> getAllUsers() {
        String json = sharedPreferences.getString(KEY_USERS, null);
        if (json != null) {
            Type type = new TypeToken<List<User>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public User getUser(String username, String password) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }


    public boolean isUsernameTaken(String username) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }
}