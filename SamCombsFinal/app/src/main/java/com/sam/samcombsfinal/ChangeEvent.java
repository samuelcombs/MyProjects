package com.sam.samcombsfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
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

import java.util.ArrayList;

public class ChangeEvent extends Activity implements TextView.OnEditorActionListener{

    private TextView EventName, Location, Address;
    private EditText Complete;
    private String EventItem;
    ArrayList<Attendance> events;
    String records;

    Context context;

    Attendance attendance;
    Employee employee;

    DatabaseReference databaseColumbia;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_event);

        EventName = (TextView) findViewById(R.id.EventName);
        Location = (TextView) findViewById(R.id.Location);
        Address = (TextView) findViewById(R.id.Address);
        Complete = (EditText) findViewById(R.id.Complete);


        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("employee", null);
        employee = gson.fromJson(json, Employee.class);

        gson = new Gson();
        json = sharedPrefs.getString("ChangeEvent", null);
        attendance = gson.fromJson(json, Attendance.class);

        EventName.setText(attendance.getEventName());
        Location.setText(attendance.getEventDate());
        Address.setText(attendance.getEventAddress());
        Complete.setText(attendance.getEventCheckIn());

        Complete.setOnEditorActionListener(this);





    }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        final String event = EventName.getText().toString();
        databaseColumbia = FirebaseDatabase.getInstance().getReference("Attendance");
        Query query = databaseColumbia.orderByChild("employeeNum").equalTo(attendance.getEmployeeNum());

        // Following is starting the first Thread Once this thread finishes. There is a second thread that starts up
        // within this before a screen is shown
        query.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot columbiaSnapshot: dataSnapshot.getChildren()) {
                    final Attendance attendance =columbiaSnapshot.getValue(Attendance.class);
                    String compareEvent = attendance.getEventName();
                    if (compareEvent.equals(event)) {
                        columbiaSnapshot.getRef().child("eventCheckIn").setValue( Complete.getText().toString());
                        Toast.makeText(context,  " Record updated", Toast.LENGTH_LONG).show();

                        // following retrives any events that they are scheduled to attend
                        records = "N";
                        events = new ArrayList<Attendance>();
                        databaseColumbia = FirebaseDatabase.getInstance().getReference("Attendance");
                        Query  query = databaseColumbia.orderByChild("employeeNum").equalTo(attendance.getEmployeeNum());
                        // This is the start of the second thread the starts at the end of the first thread
                        query.addListenerForSingleValueEvent(new ValueEventListener(){

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot columbiaSnapshot: dataSnapshot.getChildren() ) {
                                    Attendance attendance =columbiaSnapshot.getValue(Attendance.class);
                                    // do not allow any records that does not equal to N
                                    if (attendance.getEventCheckIn().equals("N")) {
                                        events.add(attendance);
                                        records = "Y";
                                    }
                                }

                                if (records.equals("Y")) {
                                    Gson gson = new Gson();
                                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                                    SharedPreferences.Editor e = sharedPrefs.edit();
                                    String json = gson.toJson(events);
                                    e.putString("events",json);
                                    e.commit();

                                    // This screen will display now. Will display more events the employee has to go to
                                    // this is executed at the finish of the second thread
                                    Intent intent = new Intent(context, ListEvents.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    // used this but was told this is not what you should do
                                    context.startActivity(intent);
                                } else {

                                    // This screen will display now. Employee has no more events to attend
                                    // this is executed at the finish of the second thread
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    // used this but was told this is not what you should do
                                    context.startActivity(intent);

                                    Toast.makeText(getApplicationContext(),employee.getEmployeeName() + "You are not registered for any events. Please register and then retry.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        return true;
    }
}



