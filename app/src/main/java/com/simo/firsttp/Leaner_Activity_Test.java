package com.simo.firsttp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class Leaner_Activity_Test extends AppCompatActivity {

    private EditText textViewNom ,textViewPrenom,textViewclasse;

    private ImageView imageView;

    Bitmap myPictureProfil ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaner___test);
    }

    @Override
        //https://belatar.name/images/img9998.png
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

        BitMapPicture bitMapPicture = new BitMapPicture();
        try {
            myPictureProfil = bitMapPicture.execute("https://belatar.name/images/img9998.png").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final  String URL_BASE  ="https://belatar.name";
    private static final  String URL_WS_PROFILE  ="/rest/profile.php?login=test&passwd=test&id=9998";
    private static final  String URL_IMAGES  ="/images";



    class BitMapPicture extends  AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap =null;
            try {
                URL url = new URL (urls[0]);
               HttpURLConnection httpURLConnectionBitMap =  (HttpURLConnection) url.openConnection();
                InputStream inStream = new BufferedInputStream(httpURLConnectionBitMap.getInputStream());

                 bitmap = BitmapFactory.decodeStream(inStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  bitmap;
        }
    }
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
                        System.err.println("ErrorMessage");
                    }else{
                        Log.d("photo",jsonObject.getString("photo"));
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

            imageView = findViewById(R.id.imageProfil);
            imageView.setImageBitmap(myPictureProfil);
        }
    }
}
