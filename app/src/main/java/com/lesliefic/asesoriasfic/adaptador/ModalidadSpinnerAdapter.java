package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lesliefic.asesoriasfic.modelo.Modalidad;

import java.util.List;

public class ModalidadSpinnerAdapter extends ArrayAdapter<Modalidad> {

    public ModalidadSpinnerAdapter(@NonNull Context context, @NonNull List<Modalidad> modalidades) {
        super(context, android.R.layout.simple_spinner_item, modalidades);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        Modalidad modalidad = getItem(position);

        if (modalidad != null) {
            tv.setText(modalidad.getModalidad());
        }
        return tv;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        Modalidad modalidad = getItem(position);

        if (modalidad != null) {
            tv.setText(modalidad.getModalidad());
        }
        return tv;
    }
}