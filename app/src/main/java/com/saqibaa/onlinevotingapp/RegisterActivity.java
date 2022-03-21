package com.saqibaa.onlinevotingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    ImageView logo;
    EditText etName, etEmail, Mobile, etPassword, et_re_password;
    Button next;
    TextView login;
    String email, password, mobile, re_password, name;

    private FirebaseAuth mAuth;

    String aadhaarUrl = "", voterUrl = "";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        etName = findViewById(R.id.Name);
        logo = findViewById(R.id.logo);
        etEmail = findViewById(R.id.Email);
        Mobile = findViewById(R.id.Mobile);
        etPassword = findViewById(R.id.nPassword);
        et_re_password = findViewById(R.id.rPassword);
        login = findViewById(R.id.Login);
        next = findViewById(R.id.Next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                re_password = et_re_password.getText().toString();
                mobile = Mobile.getText().toString();
                name = etName.getText().toString();

                if (name.isEmpty()) {
                    etName.setError("Enter Name");
                    etName.requestFocus();
                } else if (email.isEmpty()) {
                    etEmail.setError("Enter Email");
                    etEmail.requestFocus();
                } else if (mobile.isEmpty()) {
                    Mobile.setError("Enter Mobile Number");
                    Mobile.requestFocus();
                } else if (mobile.length() != 10) {
                    Mobile.setError("Incorrect Mobile Number");
                    Mobile.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Enter Password");
                    etPassword.requestFocus();
                } else if (password.length() < 6) {
                    etPassword.setError("Password Must be 6 Character");
                    etPassword.requestFocus();
                } else if (re_password.isEmpty()) {
                    et_re_password.setError("Re Enter Password");
                    et_re_password.requestFocus();
                } else if (!password.equals(re_password)) {
                    Toast.makeText(getApplicationContext(), "Password No Match", Toast.LENGTH_LONG).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        sendEmailVerification();
                                        sendUserData();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Email Already Register", Toast.LENGTH_LONG).show();
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

//    private void sendEmailVerification() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (firebaseUser != null) {
//            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
////                        sendUserData();
////                        progressDialog.setMessage("Please Wait");
////                        progressDialog.show();
////                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Verify E-mail", Toast.LENGTH_SHORT).show();
////                        mAuth.signOut();
////                        finish();
//                        Intent intent = new Intent(RegisterActivity.this, AadhaarActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();

        Calendar Date = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            currentDate = new SimpleDateFormat("dd-MM-yy");
            String date = currentDate.format(Date.getTime());

            Calendar Time = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(Time.getTime());

            UserProfile userProfile = new UserProfile(name, email, "+91" +mobile, voterUrl, aadhaarUrl, date, time, "", "", "user");
            myRef.child("User").child(mAuth.getUid()).setValue(userProfile);

            finish();

            Intent intent = new Intent(RegisterActivity.this, AadhaarActivity.class);
            startActivity(intent);
        }
    }
}