package com.example.simcareer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{

    private ArrayList<Team> listaTeam;
    private TeamAdapter.OnTeamListener mOnTeamListener;

    public TeamAdapter(ArrayList<Team> listaTeam, TeamAdapter.OnTeamListener onTeamListener) {
        this.listaTeam = listaTeam;
        this.mOnTeamListener = onTeamListener;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_scelta, parent, false);

        return new TeamAdapter.ViewHolder(v, mOnTeamListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        Team x = listaTeam.get(position);

        holder.tab.setText(x.getNome_team());
    }

    @Override
    public int getItemCount() {
        if (listaTeam != null) {
            return listaTeam.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final View view;
        public final TextView tab;
        TeamAdapter.OnTeamListener onTeamListener;

        public ViewHolder(View view, TeamAdapter.OnTeamListener onTeamListener) {
            super(view);
            this.view = view;
            this.onTeamListener = onTeamListener;
            tab = view.findViewById(R.id.tab);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnTeamListener.onTeamClick(getAdapterPosition());
        }

    }

    public interface OnTeamListener{
        void  onTeamClick(int position);
    }

}
