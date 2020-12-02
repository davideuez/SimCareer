package com.example.simcareer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AutoConcesseAdapter extends RecyclerView.Adapter<AutoConcesseAdapter.ViewHolder>{

    private ArrayList<Auto> listaAutoConcesse;
    private AutoConcesseAdapter.OnAutoConcessaListener mOnAutoConcessaListener;

    public AutoConcesseAdapter(ArrayList<Auto> listaAutoConcesse, AutoConcesseAdapter.OnAutoConcessaListener onAutoConcessaListener) {
        this.listaAutoConcesse = listaAutoConcesse;
        this.mOnAutoConcessaListener = onAutoConcessaListener;
    }

    @NonNull
    @Override
    public AutoConcesseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_scelta, parent, false);

        return new AutoConcesseAdapter.ViewHolder(v, mOnAutoConcessaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Auto x = listaAutoConcesse.get(position);

        holder.tab.setText(x.getNome());
    }

    @Override
    public int getItemCount() {
        if (listaAutoConcesse != null) {
            return listaAutoConcesse.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final View view;
        public final TextView tab;
        AutoConcesseAdapter.OnAutoConcessaListener onAutoConcessaListener;

        public ViewHolder(View view, AutoConcesseAdapter.OnAutoConcessaListener onAutoConcessaListener) {
            super(view);
            this.view = view;
            this.onAutoConcessaListener = onAutoConcessaListener;
            tab = view.findViewById(R.id.tab);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnAutoConcessaListener.onAutoConcessaClick(getAdapterPosition());
        }

    }

    public interface OnAutoConcessaListener{
        void  onAutoConcessaClick(int position);
    }

}
