package com.saqibaa.onlinevotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    EditText etEmail, etPassword;
    TextView forget;
    Button login, register;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressDialog= new ProgressDialog(this);

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null) {
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }

        logo = findViewById(R.id.logo);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        forget = findViewById(R.id.tvForget);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password;
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Enter Email");
                    etEmail.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Enter Password");
                    etPassword.requestFocus();
                } else {
                    validate(email, password);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(i);
            }
        });
    }


    private void validate(String email, String password){
        progressDialog.setMessage("Wait");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressDialog.dismiss();
                //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                }

            }
        });
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        boolean email_flag = firebaseUser.isEmailVerified();

        if(email_flag){
            finish();
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Verify Your E-Mail", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }
}
