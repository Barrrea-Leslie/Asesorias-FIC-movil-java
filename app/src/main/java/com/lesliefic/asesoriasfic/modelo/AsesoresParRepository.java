package com.lesliefic.asesoriasfic.modelo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class AsesoresParRepository {

    public static List<AsesorPar> listaAsesoresPar;

    public static List<AsesorPar> getListaAsesoresPar(Context context) {

        if (listaAsesoresPar != null) return listaAsesoresPar;

        try {
            InputStream is = context.getAssets().open("asesoresPar.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type type = new TypeToken<List<AsesorPar>>(){}.getType();
            listaAsesoresPar = gson.fromJson(json, type);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAsesoresPar;
    }

    public static AsesorPar getById(Context context, int id){
        for (AsesorPar e : getListaAsesoresPar(context)){
            if (e.getId() == id) return e;
        }
        return null;
    }

}
