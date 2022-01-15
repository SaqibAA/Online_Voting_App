package com.saqibaa.onlinevotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    ImageView logo;
    EditText etEmail;
    Button submit;
    TextView tvLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        mAuth = FirebaseAuth.getInstance();

        logo = findViewById(R.id.logo);
        etEmail = findViewById(R.id.etEmail);
        submit = findViewById(R.id.Submit);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgetPassword.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email;
                email = etEmail.getText().toString();

                if(email.isEmpty()){
                    etEmail.setError("Please Enter Your Register Email ID");
                    etEmail.requestFocus();
                }else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetPassword.this, "Check Your Register Email E-mail and Change Password", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ForgetPassword.this, "Enter Valid E-mail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}