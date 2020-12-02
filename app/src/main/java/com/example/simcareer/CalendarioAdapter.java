package com.example.simcareer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.ViewHolder>{

    private ArrayList<Calendario> calendarioCampionato;
    private CalendarioAdapter.OnCalendarioListener mOnCalendarioListener;

    public CalendarioAdapter(ArrayList<Calendario> calendarioCampionato, OnCalendarioListener onCalendarioListener) {
        this.calendarioCampionato = calendarioCampionato;
        this.mOnCalendarioListener = onCalendarioListener;
    }

    @NonNull
    @Override
    public CalendarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.calendario_layout, parent, false);

        return new CalendarioAdapter.ViewHolder(v, mOnCalendarioListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter.ViewHolder holder, int position) {

        Calendario x = calendarioCampionato.get(position);

        holder.data_round.setText(x.getData());
        holder.circuito.setText(x.getCircuito());

    }

    @Override
    public int getItemCount() {
        if (calendarioCampionato != null) {
            return calendarioCampionato.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final TextView data_round;
        public final TextView circuito;
        public final ImageView immagine_risultati;
        CalendarioAdapter.OnCalendarioListener onCalendarioListener;

        public ViewHolder(View view, CalendarioAdapter.OnCalendarioListener onCalendarioListener) {
            super(view);
            this.view = view;
            this.onCalendarioListener = onCalendarioListener;
            data_round = view.findViewById(R.id.data_round);
            circuito = view.findViewById(R.id.circuito_round);
            immagine_risultati = view.findViewById(R.id.classifica_round);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnCalendarioListener.onCalendarioClick(getAdapterPosition());
        }
    }

    public interface OnCalendarioListener{
        void  onCalendarioClick(int position);
    }
}
