package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.lesliefic.asesoriasfic.R;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.adaptador.CrearAsesorParAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaElegidaAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioElegidoAdapter;
import com.lesliefic.asesoriasfic.modelo.HorarioId;
import com.lesliefic.asesoriasfic.modelo.MateriaId;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorParRequest;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Horario;


import java.util.ArrayList;
import java.util.List;

public class crearAsesoresPar extends AppCompatActivity {

    ImageButton btn_regresar;
    EditText et_buscar, et_estudianteElegido;
    RecyclerView rvAsesores, rvMateriasAgregadas, rvHorariosAgregados;
    CrearAsesorParAdapter adapter;
    MateriaElegidaAdapter adapterMaterias;
    HorarioElegidoAdapter adapterHorariosSelect;
    HorarioAdapter adapterHorarios;
    EstudiantesRepository repoEstudiantes;
    CatalogosRepository repoCatalogos;
    AsesorParRepository repoAsesorPar;

    Button btn_agregar_materia, btn_agregar_horario, btn_guardar;

    private ArrayList<Materia> materiasElegidas = new ArrayList<>();
    private ArrayList<Materia> listaMaterias = new ArrayList<>();
    private ArrayList<Horario> horariosElegidos = new ArrayList<>();
    private ArrayList<Horario> listaHorarios = new ArrayList<>();
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private ArrayList<Estudiante> listaFiltrada = new ArrayList<>();


    private int id_estudianteElegido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_admin_crear_asesor_par);

        repoEstudiantes = new EstudiantesRepository();
        repoCatalogos = new CatalogosRepository();
        repoAsesorPar = new AsesorParRepository();


        CrearAsesorParAdapter.OnEstudianteClickListener clickListener =
                estudiante -> {
                    et_estudianteElegido.setText(estudiante.getNombreCompleto());
                    id_estudianteElegido = estudiante.getIdPersona();
                    Toast.makeText(
                            this,
                            "Seleccionado: " + id_estudianteElegido,
                            Toast.LENGTH_SHORT
                    ).show();

                };

        MateriaElegidaAdapter.OnEliminarClickListener clickListener1 = materia -> {
            int index = materiasElegidas.indexOf(materia);

            if(index != -1){
                materiasElegidas.remove(index);
                adapterMaterias.notifyItemRemoved(index);
            }
        };

        HorarioElegidoAdapter.OnEliminarClickListener clickListener2 = horario -> {
            int index = horariosElegidos.indexOf(horario);

            if(index != -1){
                horariosElegidos.remove(index);
                adapterHorariosSelect.notifyItemRemoved(index);
            }
        };

        obtenerEstudiantes();

        adapter = new CrearAsesorParAdapter(listaFiltrada, clickListener);

        btn_agregar_materia = findViewById(R.id.btnAgregarMateria);
        btn_agregar_horario = findViewById(R.id.btnAgregarHorario);

        rvMateriasAgregadas = findViewById(R.id.rvMateriasAsesor);
        rvMateriasAgregadas.setLayoutManager(new LinearLayoutManager(this));

        adapterMaterias = new MateriaElegidaAdapter(materiasElegidas, clickListener1);
        rvMateriasAgregadas.setAdapter(adapterMaterias);

        adapterHorariosSelect = new HorarioElegidoAdapter(horariosElegidos, clickListener2);
        rvHorariosAgregados = findViewById(R.id.rvHorariosAsesor);
        rvHorariosAgregados.setLayoutManager(new LinearLayoutManager(this));
        rvHorariosAgregados.setAdapter(adapterHorariosSelect);








        rvAsesores = findViewById(R.id.rvResultadosEstudiantes);
        et_buscar = findViewById(R.id.etBuscar);
        et_estudianteElegido = findViewById(R.id.etEstudianteElegido);
        btn_guardar = findViewById(R.id.btnGuardar);

        rvAsesores.setLayoutManager(new LinearLayoutManager(this));
        rvAsesores.setAdapter(adapter);

        btn_agregar_materia.setOnClickListener(v -> {
            abrirDialogMaterias(listaMaterias);
        });

        btn_agregar_horario.setOnClickListener(v -> {
            abrirDialogHorario();
        });

        btn_guardar.setOnClickListener(v -> {
            String estudianteElegido = et_estudianteElegido.getText().toString();

            if(estudianteElegido.isEmpty() || horariosElegidos.isEmpty() || materiasElegidas.isEmpty()){
                crearAsesorPar();
                finish();
            }



        });



        //EVENTO AL CAMBIAR DE TEXTO EN EL INPUT BUSCAR
        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String texto = s.toString().trim().toLowerCase();

                listaFiltrada.clear();

                if(!texto.isEmpty()){
                    for(Estudiante e : listaEstudiantes){
                        if(e.getNombreCompleto().trim().toLowerCase().contains(texto)){
                            listaFiltrada.add(e);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        btn_regresar = findViewById(R.id.btnRegresar);

        btn_regresar.setOnClickListener(v -> {
            finish();
        });

        obtenerCatalagos();


    }

    private boolean validarCampos() {
        if (id_estudianteElegido == 0 || et_estudianteElegido.getText().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione un estudiante", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (materiasElegidas.isEmpty()) {
            Toast.makeText(this, "Debe agregar al menos una materia", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (horariosElegidos.isEmpty()) {
            Toast.makeText(this, "Debe agregar al menos un horario", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void abrirDialogMaterias(ArrayList<Materia> listaMaterias){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_materia);
        ArrayList<Materia> listaMateriasFiltrada = new ArrayList<>();



        RecyclerView rv = dialog.findViewById(R.id.rvMaterias);
        rv.setLayoutManager(new LinearLayoutManager(this));

        MateriaAdapter.OnMateriaClickListener clickListener = materia -> {
            seleccionarMateria(materia);
            dialog.dismiss();
        };

        MateriaAdapter adapter = new MateriaAdapter(listaMateriasFiltrada, clickListener);
        rv.setAdapter(adapter);

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

                listaMateriasFiltrada.clear();

                if(!texto.isEmpty()){
                    for(Materia m : listaMaterias){
                        if(m.getMateria().trim().toLowerCase().contains(texto)){
                            listaMateriasFiltrada.add(m);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

    }

    void abrirDialogHorario(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_horario);

        RecyclerView rv = dialog.findViewById(R.id.rvHorarios);
        rv.setLayoutManager(new LinearLayoutManager(this));

        HorarioAdapter.OnHorarioClickListener clickListener = Horario -> {
            seleccionarHorario(Horario);
            dialog.dismiss();
        };

        adapterHorarios = new HorarioAdapter(listaHorarios, clickListener);
        rv.setAdapter(adapterHorarios);

        dialog.show();

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.45);

        dialog.getWindow().setLayout(width, height);

    }

    void seleccionarMateria(Materia materiaElegida){
        materiasElegidas.add(materiaElegida);
        adapterMaterias.notifyDataSetChanged();
    }

    void seleccionarHorario(Horario horarioElegido){
        horariosElegidos.add(horarioElegido);
        adapterHorariosSelect.notifyDataSetChanged();
    }

    public void toastNotificacion(String message){
        Toast.makeText(crearAsesoresPar.this, message, Toast.LENGTH_SHORT).show();
    }

    public void obtenerEstudiantes(){

        repoEstudiantes.obtenerEstudiantes(new EstudiantesRepository.ResultCallback<List<Estudiante>>() {
            @Override
            public void onSuccess(List<Estudiante> data) {
                listaEstudiantes.clear();
                listaEstudiantes.addAll(data);
                Log.d("ESTUDIANTE", "ESTUDIANTE ENCONTRADOS:" + data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresPar.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    public void obtenerCatalagos(){
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresPar.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override
            public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                listaHorarios.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresPar.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void crearAsesorPar(){

        if (!validarCampos()) {
            return;
        }

        ArrayList<MateriaId> materias = new ArrayList<>();
        ArrayList<HorarioId> horarios = new ArrayList<>();

        for(Materia m : materiasElegidas){
            materias.add(new MateriaId(m.getId_materia()));
        }
        for(Horario h : horariosElegidos){
            horarios.add(new HorarioId(h.getId_horario()));
        }

        CrearAsesorParRequest request = new CrearAsesorParRequest(id_estudianteElegido, materias, horarios);

        repoAsesorPar.crearAsesorPar(request, new AsesorParRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    Toast.makeText(
                            crearAsesoresPar.this,
                            "se creo el asesor par",
                            Toast.LENGTH_SHORT
                    ).show();

                }
                else if(data == 0){
                    Toast.makeText(
                            crearAsesoresPar.this,
                            "hubo un error, intente de nuevo",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresPar.this,
                        "hubo un error, intente de nuevo",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }



}