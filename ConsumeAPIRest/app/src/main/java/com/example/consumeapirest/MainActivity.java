package com.example.consumeapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private TextView textTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTitle = findViewById(R.id.idTextView);

        final Gson gson = new Gson();

        String url = "http://192.168.1.191:8080/spring-rest-demo/api/students";
       // String url = "https://jsonplaceholder.typicode.com/posts/1";

         StringRequest simo = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              Student theStudent = gson.fromJson(response,Student.class);
              textTitle.setText(theStudent.getFirstName());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textTitle.setText("That didn't work!");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(simo);
        queue.start();
    }
}
