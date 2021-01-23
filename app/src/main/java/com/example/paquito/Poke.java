package com.example.paquito;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.paquito.Pokemon.Pokemon;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class Poke extends BaseAdapter {
    private static LayoutInflater inflater=null;
    Context context;
    TextView uno;
    TextView dos;
    ArrayList<Pokemon> Poket;
    public Poke(Context context,ArrayList<Pokemon> Poket) {
        this.context=context;
        this.Poket=Poket;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.d("Listto",String.valueOf(Poket.size()));
        return Poket.size();
    }

    @Override
    public Object getItem(int position) {
        return Poket.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista=inflater.inflate(R.layout.elemento_lista,null);
        Chip chipeo=vista.findViewById(R.id.chip5);
        uno=vista.findViewById(R.id.Shin);
        dos=vista.findViewById(R.id.numero);
        uno.setText(Poket.get(position).getName());
        dos.setText("#"+String.valueOf(Poket.get(position).getPokedexnum()));
        chipeo.setClickable(false);
        chipeo.setFocusable(false);
        return vista;
    }
}
