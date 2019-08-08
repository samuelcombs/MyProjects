package com.sam.samcombsfinal;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText empID;
    Button login;
    Employee employee;
    DatabaseReference databaseColumbia;
    String records;
    Context context;
    ArrayList<Attendance> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_page);

        empID = (EditText) findViewById(R.id.empIDEditText);
        login = (Button) findViewById(R.id.Login);


        login.setOnClickListener(this);
        context = getApplicationContext();



    }
    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.Login:
                final String eID = empID.getText().toString();
                records = "N";


                // following retrieves Employee name if employee number is in table
                databaseColumbia = FirebaseDatabase.getInstance().getReference("Employee");
                Query query = databaseColumbia.orderByChild("employeeNum").equalTo(eID);
                // starting of a thread. The screen start up is at the end of this thread
                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot columbiaSnapshot: dataSnapshot.getChildren() ) {
                            employee = columbiaSnapshot.getValue(Employee.class);
                            records = "Y";
                            Gson gson = new Gson();
                            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor e = sharedPrefs.edit();
                            String json = gson.toJson(employee);
                            e.putString("employee",json);
                            e.commit();


                        }

                        if (records.equals("N")) {
                            Toast.makeText(view.getContext(),  " No Employee by that number", Toast.LENGTH_LONG).show();
                        }
                        else {
                            records ="N";
                            // following retrives any events that they are scheduled to attend
                            events = new ArrayList<Attendance>();
                            databaseColumbia = FirebaseDatabase.getInstance().getReference("Attendance");
                            Query  query = databaseColumbia.orderByChild("employeeNum").equalTo(eID);
                            // This is the start of another thread
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
                                        // screen displays now
                                        Intent intent = new Intent(view.getContext(), ListEvents.class);
                                        view.getContext().startActivity(intent);
                                    } else {
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
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
            default:
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //         case R.id.menu_add:
            //             startActivity(new Intent(this, newMember.class));
            //            return true;
            //         case R.id.menu_back:
            //            startActivity(new Intent(this, ListTeams.class));
            //            return true;

            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}


