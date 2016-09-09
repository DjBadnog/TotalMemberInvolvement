package com.example.abnn965.totalmemberinvolvement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaptureTargetActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText edtName;
    EditText edtSurname;
    EditText etdEmail;
    EditText edtMobile;
    EditText edtAddress;

    String cEmail;

    Button btnSubmitData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_capture);
        myDb = new DatabaseHelper(this);

        edtName = (EditText)findViewById(R.id.edtTargetName);
        edtSurname = (EditText)findViewById(R.id.editTextSName);
        etdEmail = (EditText)findViewById(R.id.edtTargertEmail);
        edtMobile = (EditText)findViewById(R.id.edtTargetMobile);
        edtAddress = (EditText)findViewById(R.id.editTargetAddres);
        btnSubmitData = (Button)findViewById(R.id.btnTargetSubmit);

        Intent intent = getIntent();
        cEmail = intent.getStringExtra("email");

    }
    //methord to insert data
   public void addTarget(){
       boolean isInsertedDays = myDb.newCheckInTarget("", "", "", "", "", "", "", etdEmail.getText().toString(), cEmail.toString());

       boolean isInserted = myDb.captureTargetData(edtName.getText().toString(),
               edtSurname.getText().toString(),
               etdEmail.getText().toString(),
               edtMobile.getText().toString(),
               edtAddress.getText().toString(),
               cEmail.toString());

       if (isInserted = true) {
           
           Toast.makeText(CaptureTargetActivity.this, "Data Submited", Toast.LENGTH_LONG).show();
           Intent homeIntent = new Intent(CaptureTargetActivity.this, HomeNavigationDrawerActivity.class);
           startActivity(homeIntent);
       }
       else {
           Toast.makeText(CaptureTargetActivity.this, "Data not Submited", Toast.LENGTH_LONG).show();
       }
   }

    public void onClickAddTarget(View v) {
        if(edtName.getText().toString().equals("")){
            edtName.setError("Please Enter name");
        }
        else if(edtSurname.getText().toString().equals("")){
            edtSurname.setError("Please Enter Surname");
        }
        else if(etdEmail.getText().toString().equals("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || etdEmail.getText().toString().equals("")){
            etdEmail.setError("Invalid email");
        }
        else if(edtMobile.getText().toString().equals("")){
            edtMobile.setError("Please enter mobile number");
        }
        else if(edtAddress.getText().toString().equals("")){
            edtAddress.setError("Please enter Address");
        }
        else{
            addTarget();
        }
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
