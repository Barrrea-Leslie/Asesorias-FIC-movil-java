package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lesliefic.asesoriasfic.modelo.Licenciatura;

import java.util.List;

public class LicenciaturaSpinnerAdapter extends ArrayAdapter<Licenciatura> {

    public LicenciaturaSpinnerAdapter(
            @NonNull Context context,
            @NonNull List<Licenciatura> licenciaturas
    ) {
        super(context, android.R.layout.simple_spinner_item, licenciaturas);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    // Vista seleccionada (cerrada)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        Licenciatura lic = getItem(position);

        if (lic != null) {
            tv.setText(lic.getLicenciatura());
        }
        return tv;
    }

    // Vista del dropdown
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        Licenciatura lic = getItem(position);

        if (lic != null) {
            tv.setText(lic.getLicenciatura());
        }
        return tv;
    }
}