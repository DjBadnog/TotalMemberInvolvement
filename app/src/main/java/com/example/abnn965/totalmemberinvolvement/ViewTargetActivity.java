package com.example.abnn965.totalmemberinvolvement;

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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewTargetActivity extends AppCompatActivity {
    ArrayAdapter<ViewTargetClass> viewTargetAdapter;
    private List<ViewTargetClass> viewTargetList = new ArrayList<ViewTargetClass>();
    ListView lsViewTarget;

    ArrayAdapter<TargetHeadersClass> targetHeaderAdapter;
    private List<TargetHeadersClass> targetHeaderList = new ArrayList<TargetHeadersClass>();
    ListView lsTargetHeader;

    private String selectedEmail;
    DatabaseHelper database;

    SQLiteDatabase db;
    private String cUserEmail,cTargetEmail;
    String casEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //populating the table layout with the row header values
        getTargetRowHeaders();
        targetHeaderAdapter = new MyListAdapterHeaders(getApplicationContext(),R.layout.layout_target_headers,targetHeaderList);
        lsTargetHeader = (ListView) findViewById(R.id.listTargetHeaders);
        lsTargetHeader.setAdapter(targetHeaderAdapter);

        //populating the table layout with values
        getViewTargetList();
        viewTargetAdapter = new MyListAdapterViewTarget(getApplicationContext(),R.layout.layout_view_target, viewTargetList);
        lsViewTarget = (ListView) findViewById(R.id.listTargetView);
        lsViewTarget.setAdapter(viewTargetAdapter);

        Intent intent = getIntent();
        casEmail = intent.getStringExtra("email");
        Toast.makeText(this, "Email:"+casEmail, Toast.LENGTH_LONG).show();

    }
    //The method for setting table row header values on the table layout
    public void getTargetRowHeaders(){
        targetHeaderList.add(new TargetHeadersClass("Name & Surname", "Days In", "Days Absent"));
    }

    //The List Adapter for setting table row header values on the table layout
    private class MyListAdapterHeaders extends ArrayAdapter<TargetHeadersClass> {
        int resource;
        ArrayList<TargetHeadersClass> headers= new ArrayList<TargetHeadersClass>();
        public MyListAdapterHeaders(Context context, int resource, List<TargetHeadersClass> objects) {
            super(context, resource, objects);
            this.resource = resource;
            headers = (ArrayList<TargetHeadersClass>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            TargetHeadersClass header =headers.get(position);

            TableRow tableRow;
            tableRow = (TableRow) itemView.findViewById(R.id.tableRowHeaderTarget);

            TextView tvNameHeader=(TextView) itemView.findViewById(R.id.tvNameHeader);
            tvNameHeader.setText(header.getNameHeader());

            TextView tvDaysIn=(TextView) itemView.findViewById(R.id.tvDaysIn);
            tvDaysIn.setText(header.getDaysInHeader());

            TextView tvDaysOut=(TextView) itemView.findViewById(R.id.tvDaysAbsent);
            tvDaysOut.setText(header.getDaysAbsentHeader());

            return itemView;
        }
    }

    //The method for fetching values from the sqlite database and displaying them on the listview on the table row
    public void getViewTargetList(){



    }

    //The List Adapter private class for populating values to the table row of the table layout
    private class MyListAdapterViewTarget extends ArrayAdapter<ViewTargetClass> {
        int resource;
        ArrayList<ViewTargetClass> targets= new ArrayList<ViewTargetClass>();
        public MyListAdapterViewTarget(Context context, int resource, List<ViewTargetClass> objects) {
            super(context, resource, objects);
            this.resource = resource;
            targets = (ArrayList<ViewTargetClass>)objects;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            View itemView = convertView;

            if(itemView == null){
                itemView = getLayoutInflater().inflate(resource,parent,false);
            }
            ViewTargetClass target = targets.get(position);

            TableRow tableRow;

            tableRow = (TableRow) itemView.findViewById(R.id.tableRowViewTarget);

            TextView tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvName.setText(target.getName());

            String daysIn = String.valueOf(target.getDaysIn());
            final TextView tvDaysIn=(TextView) itemView.findViewById(R.id.tvDaysIn);
            tvDaysIn.setText(daysIn);

            String daysAbsent = String.valueOf(target.getDaysAbsent());
            TextView tvDaysAbsent=(TextView) itemView.findViewById(R.id.tvDaysAbsent);
            tvDaysAbsent.setText(daysAbsent);

            if(position%2==0)
                tableRow.setBackgroundColor(Color.parseColor("#D2DDEF"));
            else
                tableRow.setBackgroundColor(Color.parseColor("#EAEEF7"));

            return itemView;
        }
    }
}
