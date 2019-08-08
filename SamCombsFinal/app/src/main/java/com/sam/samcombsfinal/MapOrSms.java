package com.sam.samcombsfinal;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


public class MapOrSms extends Activity implements View.OnClickListener{

    private TextView EventName, Location, Address, txtPhone;
    private EditText smsMessage;
    private Button Map, TextMessage;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    Context context;

    Attendance attendance;

    DatabaseReference databaseColumbia;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_or_sms);

        EventName = (TextView) findViewById(R.id.EventName);
        Location = (TextView) findViewById(R.id.Location);
        Address = (TextView) findViewById(R.id.Address);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        smsMessage = (EditText) findViewById(R.id.smsMessage);
        Map = (Button) findViewById(R.id.Map);
        TextMessage = (Button) findViewById(R.id.TextMessage);

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("MapOrSms", null);
        attendance = gson.fromJson(json, Attendance.class);

        EventName.setText(attendance.getEventName());
        txtPhone.setText(attendance.getEventPhone());
        Location.setText(attendance.getEventDate());
        Address.setText(attendance.getEventAddress());


        Map.setOnClickListener(this);
        TextMessage.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Map:
                getDirections(attendance.getEventAddress());
                break;
            case R.id.TextMessage:
                sendSmsByManager();
                break;
            default:
        }
    }
    public void getDirections(String address) {

        String DIRECTION_URL_API = "https://www.google.com/maps/dir/?api=1";

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+ address));
        startActivity(browserIntent);

    }

    public void sendSmsByManager() {

        try {

            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // request for Permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(txtPhone.getText().toString(),
                        null,
                        smsMessage.getText().toString(),
                        null,
                        null);

                Toast.makeText(getApplicationContext(),"Your sms has successfully sent!",
                        Toast.LENGTH_LONG).show();

            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsBySIntent() {
        // add the phone number in the data
        Uri uri = Uri.parse("smsto:" + txtPhone.getText().toString());
        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
        // add the message at the sms_body extra field
        smsSIntent.putExtra("sms_body", smsMessage.getText().toString());
        try{
            startActivity(smsSIntent);
        } catch (Exception ex) {
            Toast.makeText(context, "Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsByVIntent() {
        Intent smsVIntent = new Intent(Intent.ACTION_VIEW);
        // prompts only sms-mms clients
        smsVIntent.setType("vnd.android-dir/mms-sms");
        // extra fields for number and message respectively
        smsVIntent.putExtra("address", txtPhone.getText().toString());
        smsVIntent.putExtra("sms_body", smsMessage.getText().toString());
        try{
            startActivity(smsVIntent);
        } catch (Exception ex) {
            Toast.makeText(context, "Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


}



