package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lesliefic.asesoriasfic.modelo.Horario;

import java.util.List;

public class HorarioSpinnerAdapter extends ArrayAdapter<Horario> {

    public HorarioSpinnerAdapter(@NonNull Context context, @NonNull List<Horario> horarios) {
        super(context, android.R.layout.simple_spinner_item, horarios);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        Horario horario = getItem(position);

        if (horario != null) {
            tv.setText(horario.getHorario());
        }
        return tv;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        Horario horario = getItem(position);

        if (horario != null) {
            tv.setText(horario.getHorario());
        }
        return tv;
    }
}