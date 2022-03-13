package com.saqibaa.onlinevotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AadhaarActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    StorageReference storageReference;
    Button aadhaar, next;
    ImageButton AadhaarView;
    private FirebaseAuth mAuth;
    private final int REQ_AADHAAR = 1;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    String aadhaarUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child("Documents").child("Aadhaar Card");
        progressDialog = new ProgressDialog(this);

        aadhaar = findViewById(R.id.btnAadhaar);
        next = findViewById(R.id.Next);
        AadhaarView = findViewById(R.id.aadhaarView);


        aadhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        AadhaarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please Wait");
                progressDialog.show();
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] finalimg = byteArrayOutputStream.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child(finalimg+".jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AadhaarActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    aadhaarUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(AadhaarActivity.this,"Image Not Upload",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData() {
        databaseReference = databaseReference.child("User");
        final String uniqueKey = mAuth.getUid();

        databaseReference.child(uniqueKey).child("aadhaarImage").setValue(aadhaarUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(AadhaarActivity.this,"Image Upload Successful",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(AadhaarActivity.this, VoterCardActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AadhaarActivity.this,"Image Not Upload",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent selectImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(selectImage,REQ_AADHAAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_AADHAAR && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                aadhaar.setVisibility(View.INVISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            AadhaarView.setImageBitmap(bitmap);
            AadhaarView.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);

        }
    }
}