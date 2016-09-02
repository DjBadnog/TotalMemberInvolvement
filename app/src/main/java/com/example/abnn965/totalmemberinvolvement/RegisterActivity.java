package com.example.abnn965.totalmemberinvolvement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    private Button submit;
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText mobile;
    private EditText physicalAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDB = new DatabaseHelper(this);

        submit = (Button) findViewById(R.id.btnSubmitRegistration);
        name = (EditText) findViewById(R.id.edtMemberName);
        surname = (EditText) findViewById(R.id.edtMemberSurname);
        email = (EditText) findViewById(R.id.edtMemberEmail);
        password = (EditText) findViewById(R.id.edtMemberPassword);
        mobile = (EditText) findViewById(R.id.edtMemberMobile);
        physicalAddress = (EditText) findViewById(R.id.edtMemberAddress);
    }

    public void onClickRegisterUser(View view){

        if(name.getText().toString().equals("")){
            name.setError("Please Enter name");
        }
        else if(surname.getText().toString().equals("")){
            surname.setError("Please Enter Surname");
        }
        else if(email.getText().toString().equals("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || email.getText().toString().equals("")){
            email.setError("Invalid email");
        }
        else if(password.getText().toString().equals("")){
            password.setError("Please enter password");
        }
        else if(mobile.getText().toString().equals("")){
            mobile.setError("Please enter mobile number");
        }
        else if(physicalAddress.getText().toString().equals("")){
            physicalAddress.setError("Please enter Address");
        }
        else{
            insertData();
        }

    }

    public void insertData(){
        boolean isInserted = myDB.insertUserDetails(name.getText().toString(),
                surname.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                mobile.getText().toString(),
                physicalAddress.getText().toString());

        if (isInserted == true){
            Toast.makeText(RegisterActivity.this, "Succesfully registered", Toast.LENGTH_LONG).show();

            Intent loginIntent = new Intent(RegisterActivity.this, MainLoginActivity.class);
            startActivity(loginIntent);
        }
        else{
            Toast.makeText(RegisterActivity.this, "User not registered", Toast.LENGTH_LONG).show();
        }
    }

}
