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

        //if by mistake the user will come to the register activity and he has an account already, then he can go back using this
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
                // getting the info filled by the user in the fields present
                mail = newUserMail.getText().toString();
                password = newUserPassword.getText().toString();
                name = newUsername.getText().toString();

                Boolean isValid = isValidate(); // storing the value returned by the function isValidate()
                if(isValid){
                    registerTheUser(mail,password); //if the value is true, registering the user
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));//after registering sending the user back to the main activity
                }else{
                    //if the value is false, a toast will come up showing to fill the fields
                    Toast.makeText(RegisterActivity.this,"Please fill the above fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // this function is for checking whether the user has filled all the fields or not
    // if yes it will return true, otherwise false
    private boolean isValidate(){
        if(name.isEmpty() || password.isEmpty() || mail.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    //this is function for registering the user, using firebase
    //if the registration is successful, it will show registration successful, otherwise failed
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