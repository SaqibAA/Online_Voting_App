package com.saqibaa.onlinevotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    String name, email, num, aadhaar_num, voter_id;
    EditText u_name, u_email, u_mob, u_aadhaar, u_voter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        num = intent.getStringExtra("num");
        email = intent.getStringExtra("email");
        aadhaar_num = intent.getStringExtra("aadhaar");
        voter_id = intent.getStringExtra("voter");

        u_name = findViewById(R.id.U_Name);
        u_email = findViewById(R.id.U_Email);
        u_mob = findViewById(R.id.U_Mobile);
        u_aadhaar = findViewById(R.id.U_Aadhaar);
        u_voter = findViewById(R.id.U_Voter);


        u_name.setText(name);
        u_email.setText(email);
        u_mob.setText(num);
        u_aadhaar.setText(aadhaar_num);
        u_voter.setText(voter_id);

    }
}