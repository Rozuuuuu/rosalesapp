package com.example.rosaleslloyd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rosaleslloyd.Class.UserPrefManager;
import com.example.rosaleslloyd.model.User;

public class RegisterForm extends AppCompatActivity {

    EditText firstName, lastName, username, email, password, contact;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        contact = findViewById(R.id.editTextContact);
        submit = findViewById(R.id.buttonSubmit);
        cancel = findViewById(R.id.buttonCancel);

        submit.setOnClickListener(v -> {
            String fname = firstName.getText().toString().trim();
            String lname = lastName.getText().toString().trim();
            String uname = username.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String phone = contact.getText().toString().trim();

            if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || mail.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else if (UserPrefManager.getInstance(this).isUsernameTaken(uname)) {
                Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(fname, lname, uname, mail, pass, phone);
                UserPrefManager.getInstance(this).saveUser(user);

                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(v -> finish());
    }
}