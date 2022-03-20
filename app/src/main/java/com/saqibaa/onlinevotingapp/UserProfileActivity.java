package com.saqibaa.onlinevotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    String name, email, number;
    EditText u_name, u_email, u_mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        number = intent.getStringExtra("num");
        email = intent.getStringExtra("email");

        u_name = findViewById(R.id.U_Name);
        u_email = findViewById(R.id.U_Email);
        u_mob = findViewById(R.id.U_Mobile);

        u_name.setText(name);
        u_email.setText(email);
        u_mob.setText(number);

    }
}