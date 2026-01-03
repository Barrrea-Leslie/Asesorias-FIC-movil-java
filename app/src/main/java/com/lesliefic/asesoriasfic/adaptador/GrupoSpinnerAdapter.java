package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lesliefic.asesoriasfic.modelo.Grupo;

import java.util.List;

public class GrupoSpinnerAdapter extends ArrayAdapter<Grupo> {

    public GrupoSpinnerAdapter(
            @NonNull Context context,
            @NonNull List<Grupo> grupos
    ) {
        super(context, android.R.layout.simple_spinner_item, grupos);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    // Vista seleccionada (la que se ve cerrada)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        Grupo grupo = getItem(position);

        if (grupo != null) {
            tv.setText(grupo.getGrupo());
        }
        return tv;
    }

    // Vista del dropdown
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        Grupo grupo = getItem(position);

        if (grupo != null) {
            tv.setText(grupo.getGrupo());
        }
        return tv;
    }
}