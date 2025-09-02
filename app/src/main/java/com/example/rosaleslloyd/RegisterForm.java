package com.example.rosaleslloyd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterForm extends AppCompatActivity {

    EditText firstName, lastName, email, password, contact;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        // Initialize views
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        contact = findViewById(R.id.editTextContact);
        submit = findViewById(R.id.buttonSubmit);
        cancel = findViewById(R.id.buttonCancel);

        // Submit button logic
        submit.setOnClickListener(v -> {
            String fname = firstName.getText().toString().trim();
            String lname = lastName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String phone = contact.getText().toString().trim();

            if (fname.isEmpty() || lname.isEmpty() || mail.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // You can save this data to a database or pass it to another activity
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                // Example: go back to Dashboard
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Cancel button logic
        cancel.setOnClickListener(v -> finish());
    }
}