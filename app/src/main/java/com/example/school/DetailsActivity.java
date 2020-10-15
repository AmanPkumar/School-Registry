package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    private EditText detailName;
    private EditText detailAge;
    private EditText detailClass;
    private EditText contactNumber;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailName = findViewById(R.id.detailName);
        detailAge = findViewById(R.id.detailAge);
        detailClass = findViewById(R.id.detailClass);
        contactNumber = findViewById(R.id.contactNumber);
        btnSubmit = findViewById(R.id.buttonSubmitDetails);
    }
}