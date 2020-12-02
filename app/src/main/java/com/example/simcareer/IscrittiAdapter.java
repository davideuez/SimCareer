package com.example.simcareer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IscrittiAdapter extends RecyclerView.Adapter<IscrittiAdapter.ViewHolder>{

    private ArrayList<Iscritto> elencoIscritti;
    private IscrittiAdapter.OnIscrittoListener mOnIscrittoListener;

    public IscrittiAdapter(ArrayList<Iscritto> elencoIscritti, OnIscrittoListener onIscrittoListener) {
        this.elencoIscritti = elencoIscritti;
        this.mOnIscrittoListener = onIscrittoListener;
    }

    @NonNull
    @Override
    public IscrittiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.iscritti_layout, parent, false);

        return new IscrittiAdapter.ViewHolder(v, mOnIscrittoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull IscrittiAdapter.ViewHolder holder, int position) {

        Iscritto x = elencoIscritti.get(position);

        holder.nome_iscritto.setText(x.getNome());
        holder.team_iscritto.setText(x.getTeam());
        holder.auto_utilizzata.setText(x.getAuto());

    }

    @Override
    public int getItemCount() {
        if (elencoIscritti != null) {
            return elencoIscritti.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final TextView nome_iscritto;
        public final TextView team_iscritto;
        public final TextView auto_utilizzata;
        IscrittiAdapter.OnIscrittoListener onIscrittoListener;

        public ViewHolder(View view, IscrittiAdapter.OnIscrittoListener onIscrittoListener) {
            super(view);
            this.view = view;
            this.onIscrittoListener = onIscrittoListener;
            nome_iscritto = view.findViewById(R.id.nome);
            team_iscritto = view.findViewById(R.id.nome_team);
            auto_utilizzata = view.findViewById(R.id.nome_auto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnIscrittoListener.onIscrittoClick(getAdapterPosition());
        }
    }

    public interface OnIscrittoListener{
        void  onIscrittoClick(int position);
    }
}
