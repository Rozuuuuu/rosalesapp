package com.example.rosaleslloyd;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);

    Button smsbutton = findViewById(R.id.button_sms);
    Button callbutton = findViewById(R.id.button_call);
    Button emailbutton = findViewById(R.id.button_email);
    Button logoutbutton = findViewById(R.id.button_second);

    smsbutton.setOnClickListener(v -> sendSMS());
    callbutton.setOnClickListener(v -> makeCall());
    emailbutton.setOnClickListener(v -> sendEmail());
    logoutbutton.setOnClickListener(v -> logout());
    }


    private void sendSMS() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:09219978995")); // Recipient number
        intent.putExtra("sms_body", "Hello from Dashboard!"); // Message body
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
