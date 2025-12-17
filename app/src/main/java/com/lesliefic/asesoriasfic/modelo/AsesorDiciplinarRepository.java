package com.lesliefic.asesoriasfic.modelo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class AsesorDiciplinarRepository {

    public static List<AsesorDiciplinar> listaAsesoresDiciplinar;

    public static List<AsesorDiciplinar> getListaAsesoresDiciplinar(Context context) {

        if (listaAsesoresDiciplinar != null) return listaAsesoresDiciplinar;

        try {
            InputStream is = context.getAssets().open("asesoresDiciplinares.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type type = new TypeToken<List<AsesorDiciplinar>>(){}.getType();
            listaAsesoresDiciplinar = gson.fromJson(json, type);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAsesoresDiciplinar;
    }

    public static AsesorDiciplinar getById(Context context, int id){
        for (AsesorDiciplinar e : getListaAsesoresDiciplinar(context)){
            if (e.getId() == id) return e;
        }
        return null;
    }

}
