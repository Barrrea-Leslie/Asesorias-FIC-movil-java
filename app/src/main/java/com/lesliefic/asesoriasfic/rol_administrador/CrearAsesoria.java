package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoresElegirAdapter;
import com.lesliefic.asesoriasfic.adaptador.EstudianteAutoCompleteAdapter;
import com.lesliefic.asesoriasfic.adaptador.EstudianteElegirAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminCrearAsesoriaBinding;
import com.lesliefic.asesoriasfic.modelo.Asesores;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.repositorios.AsesoriasCursoRepository;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;

import java.util.ArrayList;
import java.util.List;

public class CrearAsesoria extends DrawerBaseActivity {

    private ImageButton btn_regresar;
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private List<Asesores> listaAsesores = new ArrayList<>();
    private EstudiantesRepository repoEstudiantes;
    private AsesoriasCursoRepository repoAsesorias;
    private ActivityAdminCrearAsesoriaBinding binding;

    EstudianteAutoCompleteAdapter adapterEstudiantes;

    EstudianteElegirAdapter adapterElegirEst;
    AsesoresElegirAdapter adapterElegirAse;

    EditText campoEstudiante, campoAsesor;
    Button btn_agregar_estudiante, btn_agregar_asesor;


    private int id_estudiante_elegido;
    private int id_asesor_elegido;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_crear_asesoria);
        repoEstudiantes = new EstudiantesRepository();
        repoAsesorias = new AsesoriasCursoRepository();
        btn_regresar = findViewById(R.id.btnRegresar);
        btn_agregar_asesor = findViewById(R.id.btnAgregarAsesor);
        btn_agregar_estudiante = findViewById(R.id.btnAgregarEstudiante);


        campoEstudiante = findViewById(R.id.campoEstudiante);
        campoAsesor = findViewById(R.id.campoAsesor);



        btn_regresar.setOnClickListener(v -> {
            finish();
        });

        btn_agregar_estudiante.setOnClickListener(v -> {
            abrirDialogEstudiante();
        });

        btn_agregar_asesor.setOnClickListener(view -> {
            abrirDialogAsesor();
        });

        obtenerEstudiantes();
        obtenerTodosAsesores();


    }

    public void obtenerEstudiantes(){
        repoEstudiantes.obtenerEstudiantes(new EstudiantesRepository.ResultCallback<List<Estudiante>>() {
            @Override
            public void onSuccess(List<Estudiante> data) {
                listaEstudiantes.clear();
                listaEstudiantes.addAll(data);


            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void obtenerTodosAsesores(){
        repoAsesorias.obtenerTodosAsesores(new AsesoriasCursoRepository.ResultCallback<List<Asesores>>() {
            @Override
            public void onSuccess(List<Asesores> data) {
                listaAsesores.clear();
                listaAsesores.addAll(data);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    void abrirDialogEstudiante(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_estudiante);
        ArrayList<Estudiante> listaEstudianteFiltrada = new ArrayList<>();



        RecyclerView rv = dialog.findViewById(R.id.rvEstudiantes);
        rv.setLayoutManager(new LinearLayoutManager(this));



        adapterElegirEst = new EstudianteElegirAdapter(listaEstudianteFiltrada, estudiante -> {
            campoEstudiante.setText(estudiante.getNombreCompleto());
            id_estudiante_elegido = estudiante.getIdPersona();
            dialog.dismiss();
        });
        rv.setAdapter(adapterElegirEst);

        dialog.show();

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.45);

        dialog.getWindow().setLayout(width, height);

        EditText et_buscar;
        et_buscar = dialog.findViewById(R.id.etBuscar);



        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String texto = s.toString().trim().toLowerCase();

                listaEstudianteFiltrada.clear();

                if(!texto.isEmpty()){
                    for(Estudiante e : listaEstudiantes){
                        if(e.getNombreCompleto().trim().toLowerCase().contains(texto)){
                            listaEstudianteFiltrada.add(e);
                        }
                    }
                }

                adapterElegirEst.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });



    }

    void abrirDialogAsesor(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_asesor);
        ArrayList<Asesores> listaAsesorFiltrada = new ArrayList<>();



        RecyclerView rv = dialog.findViewById(R.id.rvAsesor);
        rv.setLayoutManager(new LinearLayoutManager(this));



        adapterElegirAse = new AsesoresElegirAdapter(listaAsesorFiltrada, Asesor -> {
            campoAsesor.setText(Asesor.getAsesor());
            id_asesor_elegido = Asesor.getId_persona();
            dialog.dismiss();
        });
        rv.setAdapter(adapterElegirAse);

        dialog.show();

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.45);

        dialog.getWindow().setLayout(width, height);

        EditText et_buscar;
        et_buscar = dialog.findViewById(R.id.etBuscar);



        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String texto = s.toString().trim().toLowerCase();

                listaAsesorFiltrada.clear();

                if(!texto.isEmpty()){
                    for(Asesores a : listaAsesores){
                        if(a.getAsesor().trim().toLowerCase().contains(texto)){
                            listaAsesorFiltrada.add(a);
                        }
                    }
                }

                adapterElegirAse.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });



    }


}