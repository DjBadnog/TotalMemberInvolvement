package com.example.abnn965.totalmemberinvolvement;
//vusani
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CaptureTargetActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    SQLiteDatabase db;

    EditText edtName;
    EditText edtSurname;
    EditText etdEmail;
    EditText edtMobile;
    EditText edtAddress;

    private static final String captureTargetURL="http://10.0.2.2/TotalMemberInvolvementDailyComit/phpfiles/captureTarget.php";

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
    private void captureTargetOnline(){
        addTarget();
        StringRequest request = new StringRequest(Request.Method.POST,captureTargetURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CaptureTargetActivity.this,"Data inserted remotely",Toast.LENGTH_LONG).show();
                        // showProgressDialog();
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CaptureTargetActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            //Hashmap method for fetching values for the current user from the sqlite database and posting it to remote mysql server
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                try {
                    JSONObject jsonObject = new JSONObject();

                    db= myDb.getReadableDatabase();

                    String query = "SELECT ID,NAME,SURNAME,EMAIL,MOBILE,PHYSICAL_ADDRESS FROM target_table WHERE EMAIL='" + etdEmail.getText().toString()+ "'";
                    Cursor c = db.rawQuery(query, null);

                    while (c.moveToNext()) {

                        int id = c.getInt(0);
                        String fName = c.getString(0);
                        String lName = c.getString(1);
                        String emailAddr = c.getString(2);
                        String mobileNr = c.getString(3);
                        String physicalAddr = c.getString(4);

                        jsonObject.put("name",fName);
                        jsonObject.put("surname",lName);
                        jsonObject.put("email",emailAddr);
                        jsonObject.put("mobile",mobileNr);
                        jsonObject.put("address",physicalAddr);

                        params.put("name",jsonObject.get("name").toString());
                        params.put("surname",jsonObject.get("surname").toString());
                        params.put("email",jsonObject.get("email").toString());
                        params.put("mobile",jsonObject.get("mobile").toString());
                        params.put("address", jsonObject.get("address").toString());

                    }
                }
                catch(Exception e){
                    Toast.makeText(CaptureTargetActivity.this,e.toString(),Toast.LENGTH_LONG).show();
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

    //METHOD TO INSERT DATA ON TARGET TABLE ON DATABASE
   public void addTarget(){
       boolean isInsertedDays = myDb.newCheckInTarget("", "", "", "", "", "", "", etdEmail.getText().toString(), cEmail.toString());

       boolean isInserted = myDb.captureTargetData(edtName.getText().toString(),
               edtSurname.getText().toString(),
               etdEmail.getText().toString(),
               edtMobile.getText().toString(),
               edtAddress.getText().toString(),
               cEmail.toString());

       if (isInserted = true) {
           
           Toast.makeText(CaptureTargetActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
           Intent homeIntent = new Intent(CaptureTargetActivity.this, HomeNavigationDrawerActivity.class);
           startActivity(homeIntent);
       }
       else {
           Toast.makeText(CaptureTargetActivity.this, "Data not Submitted", Toast.LENGTH_LONG).show();
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
            if(checkInternetConnection()) {
                captureTargetOnline();
            }
            else{
                addTarget();
            }
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
