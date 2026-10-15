package com.example.sharedpreferencesandsqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // SharedPreferences UI Components
    private EditText etPrefName, etPrefUsn;
    private Button btnSaveProfile, btnLoadProfile, btnClearProfile;
    private TextView tvProfileDisplay;

    // SQLite Database UI Components
    private EditText etDbName, etDbUsn, etDeleteId;
    private Button btnAddStudent, btnViewStudents, btnDeleteStudent;
    private TextView tvDbDisplay;
    private LinearLayout llStudentList;

    // Data persistence helpers
    private SharedPreferences sharedPreferences;
    private DatabaseHelper databaseHelper;

    private static final String PREF_NAME = "UserProfilePref";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_USN = "key_usn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences and DatabaseHelper
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);

        // Initialize SharedPreferences UI elements
        etPrefName = findViewById(R.id.etPrefName);
        etPrefUsn = findViewById(R.id.etPrefUsn);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        btnLoadProfile = findViewById(R.id.btnLoadProfile);
        btnClearProfile = findViewById(R.id.btnClearProfile);
        tvProfileDisplay = findViewById(R.id.tvProfileDisplay);

        // Initialize SQLite Database UI elements
        etDbName = findViewById(R.id.etDbName);
        etDbUsn = findViewById(R.id.etDbUsn);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnViewStudents = findViewById(R.id.btnViewStudents);
        etDeleteId = findViewById(R.id.etDeleteId);
        btnDeleteStudent = findViewById(R.id.btnDeleteStudent);
        tvDbDisplay = findViewById(R.id.tvDbDisplay);
        llStudentList = findViewById(R.id.llStudentList);

        // SharedPreferences listeners
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        btnLoadProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProfile();
            }
        });

        btnClearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearProfile();
            }
        });

        // SQLite Database listeners
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAllStudents();
            }
        });

        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });

        // Automatically load existing profile and records on startup
        loadProfile();
        displayAllStudents();
    }

    // --- SharedPreferences Operations ---
    private void saveProfile() {
        String name = etPrefName.getText().toString().trim();
        String usn = etPrefUsn.getText().toString().trim();

        if (name.isEmpty() || usn.isEmpty()) {
            Toast.makeText(this, "Please enter both Name and USN", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USN, usn);
        editor.apply();

        Toast.makeText(this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show();
        etPrefName.setText("");
        etPrefUsn.setText("");
        loadProfile();
    }

    private void loadProfile() {
        String name = sharedPreferences.getString(KEY_NAME, null);
        String usn = sharedPreferences.getString(KEY_USN, null);

        if (name != null && usn != null) {
            tvProfileDisplay.setText("Saved Profile:\nName: " + name + "\nUSN: " + usn);
        } else {
            tvProfileDisplay.setText("No profile saved in SharedPreferences.");
        }
    }

    private void clearProfile() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        tvProfileDisplay.setText("Profile cleared.");
        Toast.makeText(this, "Profile Cleared Successfully", Toast.LENGTH_SHORT).show();
    }

    // --- SQLite Database Operations ---
    private void addStudent() {
        String name = etDbName.getText().toString().trim();
        String usn = etDbUsn.getText().toString().trim();

        if (name.isEmpty() || usn.isEmpty()) {
            Toast.makeText(this, "Please enter Student Name and USN", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = databaseHelper.insertStudent(name, usn);
        if (isInserted) {
            Toast.makeText(this, "Student Record Added Successfully", Toast.LENGTH_SHORT).show();
            etDbName.setText("");
            etDbUsn.setText("");
            displayAllStudents();
        } else {
            Toast.makeText(this, "Failed to Add Student Record", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayAllStudents() {
        Cursor cursor = databaseHelper.getAllStudents();
        llStudentList.removeAllViews();

        if (cursor == null || cursor.getCount() == 0) {
            tvDbDisplay.setVisibility(View.VISIBLE);
            tvDbDisplay.setText("No records found in SQLite database.");
            return;
        }

        tvDbDisplay.setVisibility(View.GONE);

        while (cursor.moveToNext()) {
            final int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String usn = cursor.getString(2);

            View itemView = getLayoutInflater().inflate(R.layout.item_student_record, llStudentList, false);
            TextView tvItemId = itemView.findViewById(R.id.tvItemId);
            TextView tvItemName = itemView.findViewById(R.id.tvItemName);
            TextView tvItemUsn = itemView.findViewById(R.id.tvItemUsn);
            ImageButton btnItemDelete = itemView.findViewById(R.id.btnItemDelete);

            tvItemId.setText(String.valueOf(id));
            tvItemName.setText(name);
            tvItemUsn.setText("USN: " + usn);

            btnItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isDeleted = databaseHelper.deleteStudent(id);
                    if (isDeleted) {
                        Toast.makeText(MainActivity.this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                        displayAllStudents();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to Delete Record", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            llStudentList.addView(itemView);
        }

        cursor.close();
    }

    private void deleteStudent() {
        String idStr = etDeleteId.getText().toString().trim();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter Student ID to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);
        boolean isDeleted = databaseHelper.deleteStudent(id);

        if (isDeleted) {
            Toast.makeText(this, "Student Record Deleted Successfully", Toast.LENGTH_SHORT).show();
            etDeleteId.setText("");
            displayAllStudents();
        } else {
            Toast.makeText(this, "Record with ID " + id + " Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}
