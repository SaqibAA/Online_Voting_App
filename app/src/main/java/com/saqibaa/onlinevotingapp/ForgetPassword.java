package com.saqibaa.onlinevotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    ImageView logo;
    EditText etEmail;
    Button submit;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

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
                    etEmail.setError("Enter Email");
                    etEmail.requestFocus();
                }else{
                    Toast.makeText(getApplicationContext(),"Service Under Processing",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}