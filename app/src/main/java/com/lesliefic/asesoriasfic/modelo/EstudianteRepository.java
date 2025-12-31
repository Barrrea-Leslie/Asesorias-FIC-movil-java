package com.lesliefic.asesoriasfic.modelo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class EstudianteRepository {

    private static List<Estudiante> listaestudiantes;

    public static List<Estudiante> getEstudiantes(Context context) {

        if (listaestudiantes != null) return listaestudiantes;

        try {
            InputStream is = context.getAssets().open("estudiantes.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Estudiante>>(){}.getType();
            listaestudiantes = gson.fromJson(json, type);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaestudiantes;
    }

    public static Estudiante getById(Context context, int id){
        for (Estudiante e : getEstudiantes(context)){
            if (e.getIdPersona() == id) return e;
        }
        return null;
    }

}
