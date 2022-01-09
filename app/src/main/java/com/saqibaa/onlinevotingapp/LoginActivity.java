package com.saqibaa.onlinevotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    EditText email, password;
    TextView forget;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        forget = findViewById(R.id.tvForget);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = ;
            }
        });
    }
}