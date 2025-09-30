package com.example.rosaleslloyd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rosaleslloyd.model.User;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    Button smsbutton, callbutton, emailbutton, logoutbutton;
    ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        smsbutton = findViewById(R.id.button_sms);
        callbutton = findViewById(R.id.button_call);
        emailbutton = findViewById(R.id.button_email);
        logoutbutton = findViewById(R.id.button_second);
        userListView = findViewById(R.id.userListView);

        smsbutton.setOnClickListener(v -> sendSMS());
        callbutton.setOnClickListener(v -> makeCall());
        emailbutton.setOnClickListener(v -> sendEmail());
        logoutbutton.setOnClickListener(v -> logout());

        displayUsers();
    }

    private void displayUsers() {
        List<User> users = UserPrefManager.getInstance(this).getAllUsers();
        List<String> displayList = new ArrayList<>();

        for (User user : users) {
            displayList.add(user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        userListView.setAdapter(adapter);
    }

    private void sendSMS() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:09219978995"));
        intent.putExtra("sms_body", "Hello from Dashboard!");
        startActivity(intent);
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:09219978995"));
        startActivity(intent);
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:lrosales_ccs@uspf.edu.ph"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Dashboard Msg");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi there, this is lloyd");
        startActivity(intent);
    }

    private void logout() {
        finish();
    }
}