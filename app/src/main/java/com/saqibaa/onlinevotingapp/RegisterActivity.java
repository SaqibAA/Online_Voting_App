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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    ImageView logo;
    EditText etName, etEmail, Mobile, etPassword, et_re_password;
    Button register;
    TextView login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        etName = findViewById(R.id.Name);
        logo = findViewById(R.id.logo);
        etEmail = findViewById(R.id.Email);
        Mobile = findViewById(R.id.Mobile);
        etPassword = findViewById(R.id.nPassword);
        et_re_password = findViewById(R.id.rPassword);
        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password, mobile, re_password, name;
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                re_password = et_re_password.getText().toString();
                mobile = Mobile.getText().toString();
                name = etName.getText().toString();

                if(name.isEmpty()){
                    etName.setError("Enter Name");
                    etName.requestFocus();
                }else if (email.isEmpty()) {
                    etEmail.setError("Enter Email");
                    etEmail.requestFocus();
                } else if (mobile.isEmpty()) {
                    Mobile.setError("Enter Mobile Number");
                    Mobile.requestFocus();
                }else if (mobile.length() != 10) {
                    Mobile.setError("Incorrect Mobile Number");
                    Mobile.requestFocus();
                }else if (password.isEmpty()) {
                    etPassword.setError("Enter Password");
                    etPassword.requestFocus();
                } else if (password.length()<6) {
                    etPassword.setError("Password Must be 6 Character");
                    etPassword.requestFocus();
                }else if (re_password.isEmpty()) {
                    et_re_password.setError("Re Enter Password");
                    et_re_password.requestFocus();
                }else if(!password.equals(re_password)) {
                    Toast.makeText(getApplicationContext(),"Password No Match",Toast.LENGTH_LONG).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
//                                    progressBar.setVisibility(View.GONE);

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
//                                    progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}