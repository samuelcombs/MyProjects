package com.sam.samcombsfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class ListEvents extends Activity implements View.OnClickListener {

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


    TextView listEventsTextView;
    ArrayList<String> eventAddress = new ArrayList<String>();
    ArrayList<String> eventArray = new ArrayList<String>();
    String eID;
    Button listEventsLogout;


    Context context;
    Employee employee;
    DatabaseReference databaseColumbia;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_events);

        listEventsTextView = (TextView) findViewById(R.id.listEventsTextView);

        final ListView eventsListView = (ListView) findViewById(R.id.listEventsListView);
        listEventsLogout = (Button) findViewById(R.id.listEventsLogout);


        listEventsLogout.setOnClickListener(this);

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("employee", null);
        employee = gson.fromJson(json, Employee.class);


        gson = new Gson();
        json = sharedPrefs.getString("events", null);
        ArrayList<Attendance> al = new ArrayList<>();
        al = gson.fromJson(json, new TypeToken<ArrayList<Attendance>>() {
        }.getType());

        eID = employee.getEmployeeNum();
        listEventsTextView.setText(employee.getEmployeeName());

        ArrayList<String> events = new ArrayList<String>();
        // Following populates an array list used in the listView

        for (int indexOf = 0; indexOf < al.size(); indexOf++)
        {



            events.add(al.get(indexOf).getEventName() + " " + al.get(indexOf).getEventAddress() + " " + al.get(indexOf).getEventDate() + " " + al.get(indexOf).getEventPhone());
            eventArray.add(al.get(indexOf).getEventName() + ":" + al.get(indexOf).getEventAddress() + ":" + al.get(indexOf).getEventDate() + ":" + al.get(indexOf).getEventPhone());
            // Following populates a second array list used in the ChangeEvent Screen
//            eventAddress.add(al.get(indexOf).getEventName() + "," + al.get(indexOf).getEventAddress() + "," + al.get(indexOf).getEventDate() + "," + al.get(indexOf).getEventPhone());
        }

        // Following populates a second array list used in the ChangeEvent Screen

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.
                simple_list_item_1, events);

        arrayAdapter.notifyDataSetChanged();

        eventsListView.setAdapter(arrayAdapter);




        //long click to delete data
        eventsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> listView, View view,
                                           final int position, long id) {
                final View v = view;
                // Get the object, positioned to the corresponding row in the result set that was clicked
                int index = eventsListView.getSelectedItemPosition();

                Attendance attendance = new Attendance();
                String fullInformation =  eventArray.get(position);
                // Get the state's capital from this row in the database.

                String [] fullInfo = fullInformation.split(":");
                attendance.setEventName(fullInfo[0]);
                attendance.setEventAddress(fullInfo[1]);
                attendance.setEventDate(fullInfo[2]);
                attendance.setEventPhone(fullInfo[3]);
                attendance.setEmployeeNum(eID);

                Gson gson = new Gson();
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor e = sharedPrefs.edit();
                String json = gson.toJson(attendance);
                e.putString("ChangeEvent",json);
                e.commit();
                Intent intent = new Intent(view.getContext(), ChangeEvent.class);
                view.getContext().startActivity(intent);

                return true;
            }


        });

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override               //list view that is tapped (<?> means generic for different views
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = eventsListView.getSelectedItemPosition();
                String fullAddress = eventArray.get(position);

                Attendance attendance = new Attendance();
                String fullInformation =  eventArray.get(position);
                // Get the state's capital from this row in the database.

                String [] fullInfo = fullInformation.split(":");
                attendance.setEventName(fullInfo[0]);
                attendance.setEventAddress(fullInfo[1]);
                attendance.setEventDate(fullInfo[2]);
                attendance.setEventPhone(fullInfo[3]);
                attendance.setEmployeeNum(eID);

                Gson gson = new Gson();
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor e = sharedPrefs.edit();
                String json = gson.toJson(attendance);
                e.putString("MapOrSms",json);
                e.commit();




                Intent intent = new Intent(view.getContext(), MapOrSms.class);
                view.getContext().startActivity(intent);

            }


        });


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }


}
