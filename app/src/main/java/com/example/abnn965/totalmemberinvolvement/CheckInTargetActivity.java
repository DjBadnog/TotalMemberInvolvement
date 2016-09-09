package com.example.abnn965.totalmemberinvolvement;
//Created by Vusi  Ngwenya

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckInTargetActivity extends AppCompatActivity {
    DatabaseHelper database;
    SQLiteDatabase db;

    TargetClass target;
    TableRow tr;

    private String selectedEmail;
    private String cEmail;

    ArrayAdapter<TargetClass> targetAdapter;
    private List<TargetClass> targetList = new ArrayList<TargetClass>();
    ListView lsTarget;

    ArrayAdapter<RowHeadersClass> headerAdapter;
    private List<RowHeadersClass> headerList = new ArrayList<RowHeadersClass>();
    ListView lsHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseHelper(this);

        //populating the table layout with the row header values
        getTableRowHeaders();
        headerAdapter = new MyListAdapterHeaders(getApplicationContext(),R.layout.layout_table_headers,headerList);
        lsHeader = (ListView) findViewById(R.id.listHeaders);
        lsHeader.setAdapter(headerAdapter);

        //populating the table layout with values
        getTargetList();
        targetAdapter = new MyListAdapterTarget(getApplicationContext(),R.layout.layout_grid_items,targetList);
        lsTarget = (ListView) findViewById(R.id.list);
        lsTarget.setAdapter(targetAdapter);
    }

    //The method for fetching values from the sqlite database and displaying them on the listview on the table row
    public void getTargetList(){
        Intent intent = getIntent();
        cEmail = intent.getStringExtra("email");

        String TABLE = "target_table";
        String query = "SELECT ID,NAME,SURNAME,EMAIL,MOBILE,PHYSICAL_ADDRESS,USER_EMAIL_ADDRESS FROM " +TABLE+ " WHERE USER_EMAIL_ADDRESS ='"+cEmail.toString()+"'";
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            int userId = cursor.getInt(0);
            String name=cursor.getString(1);
            String surname=cursor.getString(2);
            String email=cursor.getString(3);
            String mobile=cursor.getString(4);
            String address=cursor.getString(5);
            String user_email = cursor.getString(6);

            targetList.add(new TargetClass(name,surname,email,address));
        }
    }

    //The List Adapter private class for populating values to the table row of the table layout
    private class MyListAdapterTarget extends ArrayAdapter<TargetClass> {
        int resource;
        ArrayList<TargetClass> targets= new ArrayList<TargetClass>();
        public MyListAdapterTarget(Context context, int resource, List<TargetClass> objects) {
            super(context, resource, objects);
            this.resource = resource;
            targets = (ArrayList<TargetClass>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            target =targets.get(position);

            TableRow tableRow;

            tableRow = (TableRow) itemView.findViewById(R.id.tableRow);

            TextView tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvName.setText(target.getName());

            final TextView tvEmail=(TextView) itemView.findViewById(R.id.tvEmail);
            tvEmail.setText(target.getEmail());

            TextView tvAddress=(TextView) itemView.findViewById(R.id.tvAddress);
            tvAddress.setText(target.getAddress());

            Button bCheckin = (Button) itemView.findViewById(R.id.btnCheckIn);
            Button bCalls = (Button) itemView.findViewById(R.id.btnCalls);

            if(position%2==0)
                tableRow.setBackgroundColor(Color.parseColor("#D2DDEF"));
            else
                tableRow.setBackgroundColor(Color.parseColor("#EAEEF7"));


            //The onClick listener event for checking in the members who where present on the day
            bCheckin.setOnClickListener(new View.OnClickListener(){

                @TargetApi(24)
                @Override
                public void onClick(View view) {
                    db = database.getReadableDatabase();
                    selectedEmail = tvEmail.getText().toString();

                    String query = "SELECT ID,NAME,SURNAME,EMAIL,MOBILE,PHYSICAL_ADDRESS FROM target_table WHERE EMAIL='"+selectedEmail+"'";
                    Cursor cursor = db.rawQuery(query,null);

                    while(cursor.moveToNext()) {
                        int userId = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String surname = cursor.getString(2);
                        String email = cursor.getString(3);
                        String mobile = cursor.getString(4);
                        String address = cursor.getString(5);

                        Calendar calendar = Calendar.getInstance();
                        Date dNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("EEEE");
                        ft.format(dNow);
                        String currentDay=ft.format(dNow).toString();
                        calendar.setTime(dNow);
                        String id=String.valueOf(userId);

                        if (currentDay.equals("Sunday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInSunday(userId, "Present");
                        }
                        else if (currentDay.equals("Monday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInMonday(userId, "Present");
                        }
                        else if (currentDay.equals("Tuesday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInTuesday(userId, "Present");
                        }
                        else if (currentDay.equals("Wednesday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInWednesday(userId, "Present");
                        }
                        else if (currentDay.equals("Thursday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInThursday(userId, "Present");
                        }
                        else if (currentDay.equals("Friday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInFriday(userId, "Present");
                        }
                        else if (currentDay.equals("Saturday")) {
                            Toast.makeText(CheckInTargetActivity.this, "Checked In Successfully", Toast.LENGTH_SHORT).show();
                            database.checkInSaturday(userId, "Present");
                        }
                    }

                }
            });

            //The onClick listener event for checking in the members who where absent on the day
            bCalls.setOnClickListener(new View.OnClickListener(){

                @TargetApi(24)
                @Override
                public void onClick(View view) {

                    db = database.getReadableDatabase();
                    selectedEmail = tvEmail.getText().toString();

                    String query = "SELECT ID,NAME,SURNAME,EMAIL,MOBILE,PHYSICAL_ADDRESS FROM target_table WHERE EMAIL='"+selectedEmail+"'";
                    Cursor cursor = db.rawQuery(query,null);

                    while(cursor.moveToNext()) {
                        int userId = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String surname = cursor.getString(2);
                        String email = cursor.getString(3);
                        String mobile = cursor.getString(4);
                        String address = cursor.getString(5);

                        Intent callsIntent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse(mobile));
                        startActivity(callsIntent);

                        Calendar calendar = Calendar.getInstance();
                        Date dNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("EEEE");
                        ft.format(dNow);
                        String currentDay=ft.format(dNow).toString();
                        calendar.setTime(dNow);
                        String id=String.valueOf(userId);

                        if (currentDay.equals("Sunday")) {
                            database.checkInSunday(userId, "Absent");
                        }
                        else if (currentDay.equals("Monday")) {
                            database.checkInMonday(userId, "Absent");
                        }
                        else if (currentDay.equals("Tuesday")) {
                            database.checkInTuesday(userId, "Absent");
                        }
                        else if (currentDay.equals("Wednesday")) {
                            database.checkInWednesday(userId, "Absent");
                        }
                        else if (currentDay.equals("Thursday")) {
                            database.checkInThursday(userId, "Absent");
                        }
                        else if (currentDay.equals("Friday")) {
                            database.checkInFriday(userId, "Absent");
                        }
                        else if (currentDay.equals("Saturday")) {
                            database.checkInSaturday(userId, "Absent");
                        }
                    }
                }
            });

            return itemView;
        }
    }

    //The method for setting table row header values on the table layout
    public void getTableRowHeaders(){
        headerList.add(new RowHeadersClass("Full Name","Email","Address","Actions"));
    }

    //The List Adapter for setting table row header values on the table layout
    private class MyListAdapterHeaders extends ArrayAdapter<RowHeadersClass> {
        int resource;
        ArrayList<RowHeadersClass> headers= new ArrayList<RowHeadersClass>();
        public MyListAdapterHeaders(Context context, int resource, List<RowHeadersClass> objects) {
            super(context, resource, objects);
            this.resource = resource;
            headers = (ArrayList<RowHeadersClass>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            RowHeadersClass header =headers.get(position);

            TableRow tableRow;
            tableRow = (TableRow) itemView.findViewById(R.id.tableRow);

            TextView tvNameHeader=(TextView) itemView.findViewById(R.id.tvNameHeader);
            tvNameHeader.setText(header.getNameHeader());

            TextView tvEmailHeader=(TextView) itemView.findViewById(R.id.tvEmailHeader);
            tvEmailHeader.setText(header.getEmailHeader());

            TextView tvAddressHeader=(TextView) itemView.findViewById(R.id.tvAddressHeader);
            tvAddressHeader.setText(header.getAddressHeader());

            TextView tvActionsHeader=(TextView) itemView.findViewById(R.id.tvActionsHeader);
            tvActionsHeader.setText(header.getActionsHeader());

            return itemView;
        }
    }
}