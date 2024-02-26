package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextLastName, editTextFirstName;
    Button buttonRegister;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLastName = findViewById(R.id.editTextLastName);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        buttonRegister = findViewById(R.id.buttonRegister);
        dbHelper = new DatabaseHelper(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }

    private void registerStudent() {
        String lastName = editTextLastName.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();

        if (lastName.isEmpty() || firstName.isEmpty()) {
            Toast.makeText(this, "Please enter both first name and last name.", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lastName", lastName);
        values.put("firstName", firstName);

        long newRowId = db.insert("Students", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Student registered successfully.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error registering student.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
