package com.example.abnn965.totalmemberinvolvement;

import android.annotation.TargetApi;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    SQLiteDatabase db;

    private static final String registerURL="http://10.0.2.2/TotalMemberInvolvementDailyComit/phpfiles/insertLocal.php";

    private Button submit;
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText mobile;
    private EditText physicalAddress;

    private String cUserEmail;

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

        Intent intent = getIntent();
        cUserEmail = intent.getStringExtra("email");
    }
    public boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            Toast.makeText(this, "Network State: Connected ", Toast.LENGTH_LONG).show();

            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(this, "Network State: Not Connected ", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void insertDataOnline(){
        insertData();
        StringRequest request = new StringRequest(Request.Method.POST,registerURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this,"Data inserted remotely",Toast.LENGTH_LONG).show();
                        // showProgressDialog();
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            //Hashmap method for fetching values for the current user from the sqlite database and posting it to remote mysql server
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                try {
                    JSONObject jsonObject = new JSONObject();

                    db = myDB.getReadableDatabase();

                    String query = "SELECT ID,NAME,SURNAME,EMAIL,PASSWORD,MOBILE,PHYSICAL_ADDRESS FROM user_table WHERE EMAIL='" + email.getText().toString()+ "'";
                    Cursor c = db.rawQuery(query, null);

                    while (c.moveToNext()) {

                        int id = c.getInt(0);
                        String fName = c.getString(0);
                        String lName = c.getString(1);
                        String emailAddr = c.getString(2);
                        String passWrd = c.getString(3);
                        String mobileNr = c.getString(4);
                        String physicalAddr = c.getString(5);

                        jsonObject.put("name",fName);
                        jsonObject.put("surname",lName);
                        jsonObject.put("email",emailAddr);
                        jsonObject.put("password",passWrd);
                        jsonObject.put("mobile",mobileNr);
                        jsonObject.put("address",physicalAddr);

                        params.put("name",jsonObject.get("name").toString());
                        params.put("surname",jsonObject.get("surname").toString());
                        params.put("email",jsonObject.get("email").toString());
                        params.put("password",jsonObject.get("password").toString());
                        params.put("mobile",jsonObject.get("mobile").toString());
                        params.put("address", jsonObject.get("address").toString());

                    }
                }
                catch(Exception e){
                    Toast.makeText(RegisterActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }

                return params;
            }
        };

        int socketTimeout = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        request.setRetryPolicy(policy);
        MySingleton.getInstance(this).AddToRequestQueue(request);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClickRegisterUser(View view) throws JSONException, IOException {

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
        else {
            //Checking network status and posting data to remote server when there is an internet connection
            if(checkInternetConnection()) {

                insertDataOnline();
            }
            else {
                insertData();
            }
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
