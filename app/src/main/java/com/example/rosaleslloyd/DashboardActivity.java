package com.example.rosaleslloyd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rosaleslloyd.Class.UserPrefManager;
import com.example.rosaleslloyd.model.User;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private TableLayout userTable;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userTable = findViewById(R.id.userTable);
        logoutButton = findViewById(R.id.button_second);

        logoutButton.setOnClickListener(v -> finish());

        displayUsers();
    }

    private void displayUsers() {
        List<User> users = UserPrefManager.getInstance(this).getAllUsers();

        if (users.isEmpty()) {
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add header row
        TableRow headerRow = new TableRow(this);
        headerRow.setPadding(8, 8, 8, 8);

        headerRow.addView(createHeaderCell("Name"));
        headerRow.addView(createHeaderCell("Username"));
        headerRow.addView(createHeaderCell("Email"));

        userTable.addView(headerRow);

        // Add user rows
        for (User user : users) {
            TableRow row = new TableRow(this);
            row.setPadding(8, 8, 8, 8);

            row.addView(createCell(user.getFirstName() + " " + user.getLastName()));
            row.addView(createCell(user.getUsername()));
            row.addView(createCell(user.getEmail()));

            userTable.addView(row);
        }
    }

    private TextView createHeaderCell(String text) {
        TextView cell = new TextView(this);
        cell.setText(text);
        cell.setTypeface(null, Typeface.BOLD);
        cell.setGravity(Gravity.CENTER);
        cell.setPadding(16, 16, 16, 16);
        return cell;
    }

    private TextView createCell(String text) {
        TextView cell = new TextView(this);
        cell.setText(text);
        cell.setGravity(Gravity.CENTER);
        cell.setPadding(16, 16, 16, 16);
        return cell;
    }
}