package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.MaterialEstudio;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    private List<MaterialEstudio> lista;
    private Context context;

    public MaterialAdapter(List<MaterialEstudio> lista) { this.lista = lista; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MaterialEstudio material = lista.get(position);
        holder.tvNombre.setText(material.getNombre());
        holder.tvTipo.setText(material.getTipo());

        // Lógica de iconos como en Flutter
        if (material.getTipo().equals("PDF")) {
            holder.ivIcono.setImageResource(R.drawable.pdf_icon); // Debes tener estos drawables

        } else {
            holder.ivIcono.setImageResource(R.drawable.doc_icon);

        }

        holder.btnDescargar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(material.getUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTipo;
        ImageView ivIcono;
        ImageButton btnDescargar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            ivIcono = itemView.findViewById(R.id.ivIcono);
            btnDescargar = itemView.findViewById(R.id.btnDescargar);
        }
    }
}
