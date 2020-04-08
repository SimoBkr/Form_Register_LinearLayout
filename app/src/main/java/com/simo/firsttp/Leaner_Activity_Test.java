package com.simo.firsttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Leaner_Activity_Test extends AppCompatActivity {

    private EditText textViewNom ,textViewPrenom,textViewclasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaner___test);
    }

    @Override

        protected void onResume() {
        super.onResume();
        Log.d("Simo", "on est dans onResume");
        /*StringRequest req=new StringRequest(Request.Method.GET,
            "https://belatar.name/rest/profile.php?login=test&passwd=test&id=9998",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.w(DEBUGTAG,response);
                }
            }, new Response.ErrorListener() {
            @Override
                public void onErrorResponse(VolleyError error) {
                    Log.w(DEBUGTAG,error.getMessage());
                }
            }
        );
        VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(req);
    }*/

        MonWS ws = new MonWS();
        ws.execute(URL_BASE,URL_WS_PROFILE,URL_IMAGES);
    }

    private static final  String URL_BASE  ="https://belatar.name";
    private static final  String URL_WS_PROFILE  ="/rest/profile.php?login=test&passwd=test&id=9998";
    private static final  String URL_IMAGES  ="/images";


    class  MonWS extends AsyncTask<String,Void,Student> {

        @Override
        protected Student doInBackground(String... urls) {

            Student student = null ;

            URL url = null ;

            try {
                 url = new URL(urls[0] + urls[1]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(inStream));
                StringBuilder sb = new StringBuilder();
                String line = null;
                try {
                    while ((line = r.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                    String result = sb.toString();
                    Log.d("simo",result);

                    JSONObject jsonObject = new JSONObject(result);
                    if(jsonObject.has("error")){

                    }else{
                        student = new Student(jsonObject.getInt("id"),jsonObject.getString("nom"),
                                jsonObject.getString("prenom"),jsonObject.getString("classe"),
                                jsonObject.getString("phone"),null);
                        }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return student;
        }

        @Override
        protected void onPostExecute(Student student) {
            super.onPostExecute(student);
            if(student == null) return ;

            textViewNom = findViewById(R.id.EditTextName);
            textViewNom.setText(student.getNom());

            textViewPrenom = findViewById(R.id.EditTextPrenom);
            textViewPrenom.setText(student.getPrenom());

            textViewclasse = findViewById(R.id.EditTextClasse);
            textViewclasse.setText(student.getClasse());
        }
    }
}
