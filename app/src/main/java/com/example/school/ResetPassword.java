package com.example.school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private EditText resetPasswordMail;
    private Button resetButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        firebaseAuth = FirebaseAuth.getInstance();

        resetPasswordMail = findViewById(R.id.resetPasswordMail);
        resetButton = findViewById(R.id.btnResetPassword);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = resetPasswordMail.getText().toString();
                if(mail.isEmpty()){
                    // mail is empty, this toast will pop up
                    Toast.makeText(ResetPassword.this,"Please give the mail",Toast.LENGTH_SHORT).show();
                }else {
                    // method to change the password for an existing user
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this,"Password reset link has been sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ResetPassword.this,MainActivity.class));
                            }else{
                                Toast.makeText(ResetPassword.this,"Please give the registered mail",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}