package com.example.note.guiavirtual.OTs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.note.guiavirtual.R;

import java.util.ArrayList;

public class OTsAdapter extends RecyclerView.Adapter<OTsAdapter.OTsViewHolder> {

    ArrayList<OTs> listaOTs;

    public OTsAdapter(ArrayList<OTs> listaOTs) {
        this.listaOTs = listaOTs;
    }

    @NonNull
    @Override
    public OTsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mis_ots, null,false);
        return new OTsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OTsViewHolder holder, int position) {
        holder.textView.setText(listaOTs.get(position).getDescOT());
    }

    @Override
    public int getItemCount() {
        return listaOTs.size();
    }

    public class OTsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public OTsViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.textView);
        }

        public void asignarDato(String s) {
            textView.setText(s);
        }
    }
}
