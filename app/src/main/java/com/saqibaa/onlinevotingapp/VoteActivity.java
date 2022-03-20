package com.saqibaa.onlinevotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class VoteActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String mob_num;
    EditText num;
    Button sent_otp;
    String o,t,p1, p2, p3, p4, otp;

    String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String symbols = "@#$%&*?";
    String numbers = "0123456789";

    String alp = Capital_chars;
    String sys =  symbols;
    String _num = numbers ;


    Random alp_method = new Random();
    Random sys_method = new Random();
    Random _num_method = new Random();

    private static final int PERMISSION_RQST_SEND = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        // Used to prevent the Os.NetworkThreadException
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("User").child(mAuth.getUid()).child("userName");

        num = findViewById(R.id.mobile_num);
        sent_otp = findViewById(R.id.sent_otp);

        Intent intent = getIntent();
        mob_num = intent.getStringExtra("num");
        num.setText(mob_num);

        sent_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                o = String.valueOf(alp.charAt(alp_method.nextInt(alp.length())));
                t = String.valueOf(sys.charAt(sys_method.nextInt(sys.length())));
                p1 = String.valueOf(_num.charAt(_num_method.nextInt(num.length())));
                p2 = String.valueOf(_num.charAt(_num_method.nextInt(num.length())));
                p3 = String.valueOf(_num.charAt(_num_method.nextInt(num.length())));
                p4 = String.valueOf(_num.charAt(_num_method.nextInt(num.length())));
                otp = o+t+p1+p2+p3+p4;
//                num.setText(otp);

                try {
                    // Construct data
                    String apiKey = "apikey=" + "NmIzOTM1NjM1MzU4NjI0ZTYxNzc2ZTQ3NjI3YTU4NTA=";
                    String message = "&message=" + "Your OTP is " + otp;
                    String sender = "&sender=" + "Votes";
                    String numbers = "&numbers=" + mob_num;

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();
                    Toast.makeText(getApplicationContext(), "OTP Sent successfully!",
                            Toast.LENGTH_LONG).show();
//                    return stringBuffer.toString();
                } catch (Exception e) {
                    System.out.println("Error SMS "+e);
//                    return "Error "+e;
                    Toast.makeText(getApplicationContext(), "OTP Not Sent, Try after some time!",
                            Toast.LENGTH_LONG).show();
                }


//                sendSMSMessage();
                //Getting intent and PendingIntent instance
//                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
//                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
//
//                //Get the SmsManager instance and call the sendTextMessage method to send message
//                SmsManager sms=SmsManager.getDefault();
//                sms.sendTextMessage(mob_num, null, "OTP 123456", pi,null);
//
//                Toast.makeText(getApplicationContext(), "OTP Sent successfully!",
//                        Toast.LENGTH_LONG).show();
            }
        });
    }

//    protected void sendSMSMessage() {
//        _num = num.getText().toString(); //we’ll get the phone number from the user
////        message = myMessage.getText().toString();//we’ll get the Message from the user
////We’ll check the permission is granted or not . If not we’ll change
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
//            }
//            else { ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_RQST_SEND);
//            }
//        }
//    }
//    //Now once the permission is there or not would be checked
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_RQST_SEND: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(_num, null, "OVS otp is 12345678", null, null);
//                    Toast.makeText(getApplicationContext(), "OTP sent.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "OTP failed, you may try again later.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
//    }

}
