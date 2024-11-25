package com.pezloja.listadetareas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.HolderTareas> {

    List<Tarea> dataSet;
    Context context;

    public AdaptadorTareas(List<Tarea> lstEmpleados) {
        this.dataSet = lstEmpleados;
    }

    @NonNull
    @Override
    public AdaptadorTareas.HolderTareas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tarea, parent, false);
        context = parent.getContext();
        return new HolderTareas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTareas.HolderTareas holder, int position) {
        Tarea t = dataSet.get(position);

        holder.str_nomTarea.setText(t.getNomTarea());
        holder.str_fecha.setText(t.getFecha());
        holder.str_hora.setText(t.getHora());
        if (t.getCategoria() == 0) {
            holder.img.setImageResource(R.drawable.ocio_200);
        } else if (t.getCategoria() == 1) {
            holder.img.setImageResource(R.drawable.deporte_200);
        } else if (t.getCategoria() == 2) {
            holder.img.setImageResource(R.drawable.trabajo_200);
        } else if (t.getCategoria() == 3) {
            holder.img.setImageResource(R.drawable.estudio_200);
        } else if (t.getCategoria() == 4) {
            holder.img.setImageResource(R.drawable.familia_200);
        } else if (t.getCategoria() == 5) {
            holder.img.setImageResource(R.drawable.amigos_200);
        }

        holder.carta.setOnClickListener(view -> {
            Intent pasarTarea = new Intent(context, EditarTarea.class);
            pasarTarea.putExtra("posTarea", position);
            context.startActivity(pasarTarea);
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class HolderTareas extends RecyclerView.ViewHolder {
        TextView str_nomTarea, str_fecha, str_hora;
        ImageView img;
        CardView carta;

        public HolderTareas(@NonNull View itemView) {
            super(itemView);
            str_nomTarea = itemView.findViewById(R.id.str_nomTarea);
            str_fecha = itemView.findViewById(R.id.str_fecha);
            str_hora = itemView.findViewById(R.id.str_hora);
            img = itemView.findViewById(R.id.img);
            carta = itemView.findViewById(R.id.carta);
        }
    }
}
