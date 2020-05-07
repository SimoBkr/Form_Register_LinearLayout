package com.example.ronde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ronde.model.Personne;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG ="MainActivity";
    private EditText editPassword ,editEmail;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void Login(View view) {

        String URL = "http://192.168.1.7:8080/personnes/search?email="+editEmail.getText().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{
                    for(int i=0;i<response.length();i++){
                        JSONObject personne = response.getJSONObject(i);
                        String email = personne.getString("email");
                        String password = personne.getString("password");
                        String role = personne.getString("role");

                        if(email.equalsIgnoreCase(editEmail.getText().toString()) && password.equals(editPassword.getText().toString())){
                            if(role.toString().equalsIgnoreCase("Admin")) {
//                                Toast..........................................................(MainActivity.this, "SUCCESS I'm Admin", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),activity_planning.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(MainActivity.this,"I'm Agent", Toast.LENGTH_SHORT).show();
                        }else
                             Toast.makeText(MainActivity.this, "Password Or Login Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

//        final Gson gson = new Gson();

//        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//               Personne personne = gson.fromJson(response,Personne.class);
//               if(personne.getEmail().equalsIgnoreCase(editEmail.getText().toString()) && personne.getPassword().equals(editPassword.getText().toString())){
//                   if(personne.getRole().toString().equalsIgnoreCase("Admin"))
//                       Toast.makeText(MainActivity.this, "SUCCESS I'm Admin", Toast.LENGTH_SHORT).show();
//                   //
//                   else
//                       Toast.makeText(MainActivity.this,"I'm Agent", Toast.LENGTH_SHORT).show();
//               }else
//                   Toast.makeText(MainActivity.this, "Password Or Login Incorrect", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//        requestQueue.start();
    }
}
