package com.example.simcareer;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CopertinaAdapter extends RecyclerView.Adapter<CopertinaAdapter.ViewHolder> {

    private ArrayList<Copertina_Campionato> copertineCampionato;
    private OnCampionatoListener mOnCampionatoListener;

    public CopertinaAdapter(ArrayList<Copertina_Campionato> copertineCampionato, OnCampionatoListener onCampionatoListener) {
        this.copertineCampionato = copertineCampionato;
        this.mOnCampionatoListener = onCampionatoListener;
    }

    @NonNull
    @Override
    public CopertinaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.campionati_layout, parent, false);

        return new ViewHolder(v, mOnCampionatoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CopertinaAdapter.ViewHolder holder, int position) {
        Copertina_Campionato x = copertineCampionato.get(position);

        holder.nome_campionato.setText(x.getNome_campionato());
        holder.data_inizio.setText(x.getInizio_campionato());
        holder.data_fine.setText(x.getFine_campionato());
    }

    @Override
    public int getItemCount() {
        if (copertineCampionato != null) {
            return copertineCampionato.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final TextView nome_campionato;
        public final TextView data_inizio;
        public final TextView data_fine;
        public final ImageView immagine_copertina;
        OnCampionatoListener onCampionatoListener;

        public ViewHolder(View view, OnCampionatoListener onCampionatoListener) {
            super(view);
            this.view = view;
            this.onCampionatoListener = onCampionatoListener;
            nome_campionato = view.findViewById(R.id.titolo_campionato);
            data_inizio = view.findViewById(R.id.data_inizio_campionato);
            data_fine = view.findViewById(R.id.data_fine_campionato);
            immagine_copertina = view.findViewById(R.id.immagine_campionato);
            itemView.setOnClickListener(this );
        }

        @Override
        public void onClick(View v) {
            onCampionatoListener.onCampionatoClick(getAdapterPosition());
        }
    }

    public interface OnCampionatoListener{
        void  onCampionatoClick(int position);
    }

}
