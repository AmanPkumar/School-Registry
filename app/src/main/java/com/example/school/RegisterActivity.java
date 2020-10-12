package com.example.school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText newUsername;
    private EditText newUserMail;
    private EditText newUserPassword;
    private Button btnRegister;
    private TextView tvLogIn;
    private FirebaseAuth firebaseAuth;

    String mail,name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUsername = findViewById(R.id.newUserName);
        newUserMail = findViewById(R.id.newUserMail);
        newUserPassword = findViewById(R.id.newUserPassword);
        btnRegister = findViewById(R.id.buttonRegister);
        tvLogIn = findViewById(R.id.tvLogIn);


        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = newUserMail.getText().toString();
                password = newUserPassword.getText().toString();
                name = newUsername.getText().toString();

                Boolean isValid = isValidate();
                if(isValid){
                    registerTheUser(mail,password);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this,"Please fill the above fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidate(){
        if(name.isEmpty() || password.isEmpty() || mail.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void registerTheUser(String userMail,String userPassword){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(userMail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}