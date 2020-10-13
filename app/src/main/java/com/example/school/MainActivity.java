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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userPassword;
    private Button loginButton;
    private TextView signInTextView;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressBar;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance(); //for getting the instance of the firebase so that the firebase can be used

        firebaseUser = firebaseAuth.getCurrentUser(); // getting the user


        userName = findViewById(R.id.etUserName);
        userPassword = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signInTextView = findViewById(R.id.tvSignIn);

        //checking if the user already signed in or not
        //so that he needs not to fill credentials all the time to log in

        if(firebaseUser != null){
            finish();
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
        }


        // this method for the users who are not registered
        // it will take them to the register activity

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(newIntent);
            }
        });

        //this is for logging the user in after his registration

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
        // progress bar is for showing the progress when clicked on log in button
        progressBar = new ProgressDialog(this);
        //message is set for the progress bar
        progressBar.setMessage("Opening, please wait...");
        //this method is to show the progress bar
        progressBar.show();

        //this is the method for the firebase to check whether this mail and password is already there or not
        firebaseAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.dismiss(); // if user is logged in then the progress bar get dismissed

                    //user will be sent to the homepage with this intent
                    Intent newIntent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(newIntent);
                }else {
                    // if there is any mistake in mail or password then a pop up message will come saying check the credentials
                    Toast.makeText(MainActivity.this,"Please fill correct credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}