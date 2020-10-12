package com.example.school;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button loginButton;
    private TextView signInTextView;

    private boolean credential = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signInTextView = findViewById(R.id.tvSignIn);

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(newIntent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputname = userName.getText().toString();
                String inputuserPassword = password.getText().toString();

                if(inputname.isEmpty() || inputuserPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the credentials",Toast.LENGTH_SHORT).show();
                }else{
                    credential = checkCredentials(inputname,inputuserPassword);
                    if(credential){
                        Intent intent = new Intent(MainActivity.this,HomePage.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"Please check the credentials",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean checkCredentials(String name, String userPassword) {
        if(name.equals("Aman") && userPassword.equals("123456")){
            return true;
        }
        return false;
    }
}