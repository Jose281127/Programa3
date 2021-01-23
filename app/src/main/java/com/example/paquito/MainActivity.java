package com.example.paquito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.paquito.Pokemon.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONObject> {
    RequestQueue Queue;
    JsonObjectRequest res;
    ListView texto;
    ArrayList<Pokemon> arrayList=new ArrayList<>();
    MediaPlayer mp;
    MediaPlayer mp2;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar2);
        Queue= Volley.newRequestQueue(this);
        texto= (ListView)findViewById(R.id.Menu);
        mp=MediaPlayer.create(this,R.raw.oro);
        mp.setLooping(true);

        res= new JsonObjectRequest(Request.Method.GET,getResources().getString(R.string.Base_url),null,this,this);

       Queue.add(res);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mp.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mp.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Nop","No s pudo");
        progressBar.setVisibility(View.GONE);
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("Listto",res.toString());
        progressBar.setVisibility(View.GONE);
        String[] nuevo ;
        JSONArray objeto;
       try{
          objeto=response.getJSONArray("results");
          Pokemon temp;

          int pos;
          String [] a={"",""};
          String nombre;

          nuevo=new String[objeto.length()];
            for (int i=0;i<objeto.length();i++){
                JSONObject pokemon=objeto.getJSONObject(i);
                nombre=pokemon.getString("name").toUpperCase().charAt(0)+pokemon.getString("name").substring(1,pokemon.getString("name").length());
                //Log.d("Listto",pokemon.getString("name"));
                temp=new Pokemon(i+1,nombre,0,0,0,a,pokemon.getString("url"),0);
                arrayList.add(temp);
                Log.d("Listto",temp.getUrl());
                //Log.d("Listto",temp.getName());
            }
           Log.d("Listto",String.valueOf(arrayList.size()));
        Poke adaptador=new Poke(this,arrayList);

            texto.setAdapter(adaptador);
            texto.setClickable(true);
           texto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Log.d("Listto","mensaje");
                   Bundle bundle=new Bundle();
                   Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                   Pokemon pokemon=(Pokemon)parent.getItemAtPosition(position);
                   bundle.putString("Pokemoon",pokemon.getUrl());
                   bundle.putInt("Numero",pokemon.getPokedexnum());

                   mp.pause();

                   mp2= MediaPlayer.create(MainActivity.this,R.raw.pokeball_open);
                   mp2.start();

                   intent.putExtras(bundle);
                   intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

                   startActivity(intent);
               }
           });

       } catch (JSONException e) {
           e.printStackTrace();
           Log.d("Listto","2ll");
       } ;

    }
}