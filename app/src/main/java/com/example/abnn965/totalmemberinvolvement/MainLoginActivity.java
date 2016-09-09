package com.example.abnn965.totalmemberinvolvement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLoginActivity extends AppCompatActivity {

    SQLiteDatabase myDb;
    DatabaseHelper dbH;

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        dbH = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.edtLoginEmail);
        password = (EditText) findViewById(R.id.edtLoginPassword);
    }

    //METHOD TO LOGIN
    public void onClickLogin(View view){

        final String TABLE_NAME = " user_table";
        String selectQuery = "SELECT * FROM " + TABLE_NAME +" WHERE EMAIL ='" + email.getText().toString() +"'";
        myDb = dbH.getReadableDatabase();
        Cursor cursor = myDb.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String emailDb = cursor.getString(3);
            String passwordDb = cursor.getString(4);
            String mobile = cursor.getString(5);
            String address = cursor.getString(6);

            String casEmail = email.getText().toString();

            if (email.getText().toString().equals(emailDb) && password.getText().toString().equals(passwordDb)){
                Intent loginIntent = new Intent(MainLoginActivity.this, HomeNavigationDrawerActivity.class);
                loginIntent.putExtra("email",casEmail);
                startActivity(loginIntent);
                Toast.makeText(MainLoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainLoginActivity.this, "User or Password don't match", Toast.LENGTH_SHORT).show();
                email.setText("");
                password.setText("");
            }

        }cursor.close();

    }
    //METHOD TO NAVIGATE TO REGISTER PAGE
    public void onClickRegister(View view){
        Intent regIntent = new Intent(MainLoginActivity.this, RegisterActivity.class);
        startActivity(regIntent);
    }
}
