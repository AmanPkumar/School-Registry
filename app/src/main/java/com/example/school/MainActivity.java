package com.example.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userPassword;
    private Button loginButton;
    private TextView signInTextView;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.etUserName);
        userPassword = findViewById(R.id.etPassword);
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
                String inputName = userName.getText().toString();
                String inputUserPassword = userPassword.getText().toString();

                if(inputName.isEmpty() || inputUserPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill the credentials",Toast.LENGTH_SHORT).show();
                }else {
                    logInTheUser(inputName,inputUserPassword);
                }


            }
        });
    }
    private void logInTheUser(String user,String password){
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Opening, please wait...");
        progressBar.show();
        firebaseAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.dismiss();
                    Intent newIntent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(newIntent);
                }else {
                    Toast.makeText(MainActivity.this,"Please fill correct credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}