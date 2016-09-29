package com.example.abnn965.totalmemberinvolvement;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.VoiceInteractor;
import android.content.Context;

/**
 * Created by ABVN237 on 9/26/2016.
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context){

        this.context = context;
        requestQueue= getRequestQueue();
    }
    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void AddToRequestQueue(Request request){
        requestQueue.add(request);
    }
}
