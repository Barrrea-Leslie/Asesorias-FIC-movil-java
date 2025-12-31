package com.lesliefic.asesoriasfic.modelo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class AsesoriaRepository {

    public static List<Asesoria> listaAsesorias;

        public static List<Asesoria> getListaAsesorias(Context context) {

        if (listaAsesorias != null) return listaAsesorias;

        try {
            InputStream is = context.getAssets().open("asesorias.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Asesoria>>(){}.getType();
            listaAsesorias = gson.fromJson(json, type);

            //para vincular estudiantes a cada asesoria
            List<Estudiante> estudiantes = EstudianteRepository.getEstudiantes(context);
            for(Asesoria a : listaAsesorias){
                for (Estudiante e : estudiantes){
                    if (a.getEstudianteId() == e.getIdPersona()){
                        a.setEstudiante(e);
                        break;
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAsesorias;
    }

    public static Asesoria getById(Context context, int id){
        for (Asesoria e : getListaAsesorias(context)){
            if (e.getId() == id) return e;
        }
        return null;
    }

}
