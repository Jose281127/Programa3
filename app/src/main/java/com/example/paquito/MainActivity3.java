package com.example.paquito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity3 extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mp=MediaPlayer.create(this,R.raw.sms);
        mp.start();
    }

    public void click(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        Animatoo.animateZoom(this);
        startActivity(intent);
        finish();
    }
}