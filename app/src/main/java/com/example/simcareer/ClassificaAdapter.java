package com.example.simcareer;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassificaAdapter extends RecyclerView.Adapter<ClassificaAdapter.ViewHolder>{

    private ArrayList<Classificato> listaClassificati;

    public ClassificaAdapter(ArrayList<Classificato> listaClassificati) {
        this.listaClassificati = listaClassificati;
    }

    @NonNull
    @Override
    public ClassificaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.classifiche_piloti_layout, parent, false);

        return new ClassificaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classificato x = listaClassificati.get(position);

        if(position==0){
            holder.medaglia.setBackgroundResource(R.drawable.oro);
        } else if(position==1) {
            holder.medaglia.setBackgroundResource(R.drawable.argento);
        } else if(position==2) {
            holder.medaglia.setBackgroundResource(R.drawable.bronzo);
        }

        int posizioneVera = position+1;

        holder.posizionePilota.setText(""+posizioneVera+"°");
        holder.nome.setText(x.getNome());
        holder.punti.setText(Integer.toString(x.getPunti())+ " PT");
        holder.nome_team.setText(x.getTeam());
        holder.auto.setText(x.getAuto());
    }

    @Override
    public int getItemCount() {
        if (listaClassificati != null) {
            return listaClassificati.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final TextView nome;
        public final TextView punti;
        public final TextView nome_team;
        public final TextView auto;
        public final TextView posizionePilota;
        public final ImageView medaglia;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nome = view.findViewById(R.id.nome);
            punti = view.findViewById(R.id.punti);
            nome_team = view.findViewById(R.id.nome_team);
            auto = view.findViewById(R.id.nome_auto);
            medaglia = view.findViewById(R.id.medaglia_placeholder);
            posizionePilota = view.findViewById(R.id.posizione_classifica);

        }

    }



}
