package com.example.paquito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.paquito.Pokemon.Pokemon;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    RequestQueue Queue;
    JsonObjectRequest res;
    ImageView pokemon;
    TextView carta;
    TextView nombre;
    TextView experiencia;
    TextView altura;
    TextView peso;
    TextView tipo;
    ImageView imatipo;
    ImageView imatipo2;
    TextView cont;
    TextView sol;
    MediaPlayer mp,mp2;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle=getIntent().getExtras();
        cont=findViewById(R.id.cont);
        sol=findViewById(R.id.sol);
        pokemon=findViewById(R.id.Vacia);
        nombre=findViewById(R.id.nombre);
        experiencia=findViewById(R.id.Experiencia);
        carta=findViewById(R.id.Carta);
        altura=findViewById(R.id.altura);
        peso=findViewById(R.id.peso);
        tipo=findViewById(R.id.tipo);
        imatipo=findViewById(R.id.tipo1);
        imatipo2=findViewById(R.id.tipo2);
        pb=findViewById(R.id.progressBar);
        Queue= Volley.newRequestQueue(this);
        res= new JsonObjectRequest(Request.Method.GET,bundle.getString("Pokemoon"),null,this,this);
        Log.d("Listto",bundle.getString("Pokemoon"));
        Queue.add(res);

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Listto","Error viejo");
        pb.setVisibility(View.GONE);
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            pb.setVisibility(View.GONE);
            String[] a={"",""};
            JSONObject objeto3;
            mp=MediaPlayer.create(this,R.raw.oro);
            Bundle bundle=getIntent().getExtras();



            Pokemon Lugia=new Pokemon(bundle.getInt("Numero"),response.getString("name"),Integer.parseInt(response.getString("height")),Integer.parseInt(response.getString("weight")),Integer.parseInt(response.getString("base_experience")),a,"",0);
            JSONArray objeto2=response.getJSONArray("types");
            JSONObject objeto=response.getJSONObject("sprites");
            objeto=objeto.getJSONObject("other");

            objeto=objeto.getJSONObject("official-artwork");

            Picasso.with(this).load(objeto.getString("front_default")).into(pokemon);
            Log.d("Listto",objeto.getString("front_default"));
            Log.d("Listto",String.valueOf(objeto2.length()));
            if(objeto2.length()<2){
                objeto3=objeto2.getJSONObject(0);
                objeto3=objeto3.getJSONObject("type");
                a[0]=objeto3.getString("name");
                a[1]="solo uno";
                Lugia.setNuev(a);
            }
            else {
                objeto3=objeto2.getJSONObject(0);
                objeto3=objeto3.getJSONObject("type");
                a[0]=objeto3.getString("name");
                objeto3=objeto2.getJSONObject(1);
                objeto3=objeto3.getJSONObject("type");
                a[1]=objeto3.getString("name");
                Log.d("Listto",a[1]);
                Lugia.setNuev(a);
            }
            nombre.setText(Lugia.getName().toUpperCase().charAt(0)+Lugia.getName().substring(1,Lugia.getName().length()));
            experiencia.setText(getResources().getString(R.string.Experiencia)+Lugia.getExp());
            altura.setText(getResources().getString(R.string.Height)+getResources().getString(R.string.espacui)+Lugia.getAltura());
            peso.setText(getResources().getString(R.string.Weight)+getResources().getString(R.string.espacui)+Lugia.getPeso());
            tipo.setText(getResources().getString(R.string.Tipos));
            imatipo.setImageResource(Encontrartipo(Lugia.getNuev()[0]));
            imatipo2.setImageResource(Encontrartipo(Lugia.getNuev()[1]));
            cont.setText(getResources().getString(R.string.Gato)+getResources().getString(R.string.esp)+agregacadenas(String.valueOf(Lugia.getPokedexnum())));
            sol.setText(getResources().getString(R.string.Gato)+getResources().getString(R.string.esp)+agregacadenas(String.valueOf(Lugia.getPokedexnum())));
            Thread timer=new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    }catch (Exception e){

                    }finally {
                        mp.start();
                        mp.setLooping(true);
                    }

                }
            };
            timer.start();

        } catch (JSONException e) {
            Log.d("Listto","Descenso fotonico");
            e.printStackTrace();
        }


    }
    public String agregacadenas(String num){
        String val=""+num.charAt(0);
        if(num.length()==1){
            return num;
        }
        else {
            for (int i=1;i<num.length();i++){
                val=val+getResources().getString(R.string.esp)+num.charAt(i);
            }
            Log.d("Listto",val);
            return val;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mp.start();
    }

    public Integer Encontrartipo(String tipo){
        if (tipo.equals("bug")){
            return R.drawable.bug;
        }
        if(tipo.equals("water")){
            return R.drawable.water;
        }
        if(tipo.equals("rock")){
            return R.drawable.rock;
        }
        if(tipo.equals("steel")){
            return R.drawable.steel;
        }
        if(tipo.equals("psychic")){
            return R.drawable.psychic;
        }
        if(tipo.equals("poison")){
            return R.drawable.poison;
        }
        if(tipo.equals("normal")){
            return R.drawable.normal;
        }
        if(tipo.equals("ice")){
            return R.drawable.ice;
        }
        if(tipo.equals("ground")){
            return R.drawable.ground;
        }
        if (tipo.equals("grass")){
            return R.drawable.grass;
        }
        if(tipo.equals("ghost")){
            return R.drawable.ghost;
        }
        if (tipo.equals("flying")){
            return R.drawable.flying;
        }
        if (tipo.equals("fire")){
            return R.drawable.fire;
        }
        if(tipo.equals("fighting")){
            return R.drawable.fighting;
        }
        if(tipo.equals("fairy")){
            return R.drawable.fairy;
        }
        if(tipo.equals("electric")){
            return R.drawable.electric;
        }
        if(tipo.equals("dragon")){
            return R.drawable.dragon;
        }
        if(tipo.equals("dark")){
            return R.drawable.dark;
        }
        return 0;
    }
}