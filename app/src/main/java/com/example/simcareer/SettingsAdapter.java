package com.example.simcareer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder>{

    private ArrayList<Settings> elencoImpostazioni;

    public SettingsAdapter(ArrayList<Settings> elencoImpostazioni) {
        this.elencoImpostazioni = elencoImpostazioni;
    }

    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_layout, parent, false);

        return new SettingsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder holder, int position) {

        Settings x = elencoImpostazioni.get(position);

        holder.titolo.setText(x.getTitolo());
        holder.descrizione.setText(x.getImpostazione());

    }

    @Override
    public int getItemCount() {
        if (elencoImpostazioni != null) {
            return elencoImpostazioni.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final TextView titolo;
        public final TextView descrizione;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            titolo = view.findViewById(R.id.titolo_setting);
            descrizione = view.findViewById(R.id.descrizione_setting);
        }

    }

}
