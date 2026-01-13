package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lesliefic.asesoriasfic.modelo.Materia;

import java.util.List;

public class MateriaSpinnerAdapter extends ArrayAdapter<Materia> {

    public MateriaSpinnerAdapter(@NonNull Context context, @NonNull List<Materia> materias) {
        super(context, android.R.layout.simple_spinner_item, materias);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        Materia materia = getItem(position);

        if (materia != null) {
            tv.setText(materia.getMateria());
        }
        return tv;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        Materia materia = getItem(position);

        if (materia != null) {
            tv.setText(materia.getMateria());
        }
        return tv;
    }
}