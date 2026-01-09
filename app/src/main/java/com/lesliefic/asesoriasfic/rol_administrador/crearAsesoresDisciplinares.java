package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.GrupoSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioElegidoAdapter;
import com.lesliefic.asesoriasfic.adaptador.LicenciaturaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaElegidaAdapter;
import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.HorarioId;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.MateriaId;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesorDisciplinarRequest;
import com.lesliefic.asesoriasfic.repositorios.AsesorDisciplinarRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;


import java.util.ArrayList;
import java.util.List;

public class crearAsesoresDisciplinares extends AppCompatActivity {




    private ArrayList<Grupo> listaGrupos = new ArrayList<>();
    private ArrayList<Licenciatura> listaLicenciaturas = new ArrayList<>();

    RecyclerView rvMateriasAgregadas, rvHorariosAgregados;

    MateriaElegidaAdapter adapterMaterias;
    HorarioElegidoAdapter adapterHorariosSelect;
    HorarioAdapter adapterHorarios;

    GrupoSpinnerAdapter adapterGrupo;
    LicenciaturaSpinnerAdapter adapterLicenciatura;
    CatalogosRepository repoCatalogos;
    AsesorDisciplinarRepository repoAsesorDisciplinar;

    Button btn_agregar_materia, btn_agregar_horario, btn_guardar;
    Spinner spGrupo, spLicenciatura;

    private List<Materia> materiasElegidas = new ArrayList<>();
    private ArrayList<Materia> listaMaterias = new ArrayList<>();
    private List<Horario> horariosElegidos = new ArrayList<>();
    private ArrayList<Horario> listaHorarios = new ArrayList<>();

    EditText campoNombre, campoApePat, campoApeMat, campoNumeroCuenta, campoCorreo, campoNumCel, campoPromedio, campoContrasena;
    ImageButton btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_crear_asesor_disciplinar);


        btnRegresar = findViewById(R.id.btnRegresar);
        repoCatalogos = new CatalogosRepository();
        repoAsesorDisciplinar = new AsesorDisciplinarRepository();



        campoNombre = findViewById(R.id.campoNombre);
        campoApePat = findViewById(R.id.campoApePat);
        campoApeMat = findViewById(R.id.campoApeMat);
        campoNumeroCuenta = findViewById(R.id.campoNumeroCuenta);
        campoCorreo = findViewById(R.id.campoCorreo);
        campoNumCel = findViewById(R.id.campoNumCel);
        campoContrasena = findViewById(R.id.campoContrasena);



        btnRegresar.setOnClickListener(v -> finish());

        MateriaElegidaAdapter.OnEliminarClickListener clickListener1 = materia -> {
            int index = materiasElegidas.indexOf(materia);
            if (index != -1) {
                materiasElegidas.remove(index);
                adapterMaterias.notifyItemRemoved(index);
            }
        };

        HorarioElegidoAdapter.OnEliminarClickListener clickListener2 = horario -> {
            int index = horariosElegidos.indexOf(horario);
            if (index != -1) {
                horariosElegidos.remove(index);
                adapterHorariosSelect.notifyItemRemoved(index);
            }
        };

        btn_agregar_materia = findViewById(R.id.btnAgregarMateria);
        btn_agregar_horario = findViewById(R.id.btnAgregarHorario);
        btn_guardar = findViewById(R.id.btnGuardar);

        btn_agregar_materia.setOnClickListener(v -> abrirDialogMaterias(listaMaterias));
        btn_agregar_horario.setOnClickListener(v -> abrirDialogHorario());
        btn_guardar.setOnClickListener(v -> crearAsesorDisciplinar());

        obtenerCatalagos();

        rvMateriasAgregadas = findViewById(R.id.rvMateriasAsesor);
        rvMateriasAgregadas.setLayoutManager(new LinearLayoutManager(this));
        adapterMaterias = new MateriaElegidaAdapter(materiasElegidas, clickListener1);
        rvMateriasAgregadas.setAdapter(adapterMaterias);

        adapterHorariosSelect = new HorarioElegidoAdapter(horariosElegidos, clickListener2);
        rvHorariosAgregados = findViewById(R.id.rvHorariosAsesor);
        rvHorariosAgregados.setLayoutManager(new LinearLayoutManager(this));
        rvHorariosAgregados.setAdapter(adapterHorariosSelect);


    }

    void abrirDialogMaterias(ArrayList<Materia> listaMaterias) {
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

        EditText et_buscar = dialog.findViewById(R.id.etBuscar);

        if (et_buscar != null) {
            et_buscar.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    String texto = s.toString().trim().toLowerCase();

                    listaMateriasFiltrada.clear();

                    if (!texto.isEmpty()) {
                        for (Materia m : listaMaterias) {
                            if (m.getMateria().trim().toLowerCase().contains(texto)) {
                                listaMateriasFiltrada.add(m);
                            }
                        }
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
    }

    void abrirDialogHorario() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_horario);

        RecyclerView rv = dialog.findViewById(R.id.rvHorarios);
        rv.setLayoutManager(new LinearLayoutManager(this));

        HorarioAdapter.OnHorarioClickListener clickListener = horario -> {
            seleccionarHorario(horario);
            dialog.dismiss();
        };

        adapterHorarios = new HorarioAdapter(listaHorarios, clickListener);
        rv.setAdapter(adapterHorarios);

        dialog.show();

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.45);
        dialog.getWindow().setLayout(width, height);
    }

    void seleccionarMateria(Materia materiaElegida) {
        materiasElegidas.add(materiaElegida);
        adapterMaterias.notifyDataSetChanged();
    }

    void seleccionarHorario(Horario horarioElegido) {
        horariosElegidos.add(horarioElegido);
        adapterHorariosSelect.notifyDataSetChanged();
    }

    public void obtenerCatalagos() {
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresDisciplinares.this,
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
                        crearAsesoresDisciplinares.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void crearAsesorDisciplinar() {
        List<MateriaId> materias = new ArrayList<>();
        List<HorarioId> horarios = new ArrayList<>();

        String textoNumeroCuenta = campoNumeroCuenta.getText().toString().trim();

        int numeroCuenta = Integer.parseInt(textoNumeroCuenta);


        if (!materiasElegidas.isEmpty()) {
            for (Materia m : materiasElegidas) {
                materias.add(new MateriaId(m.getId_materia()));
            }
        }

        if (!horariosElegidos.isEmpty()) {
            for (Horario h : horariosElegidos) {
                horarios.add(new HorarioId(h.getId_horario()));
            }
        }

        CrearAsesorDisciplinarRequest request = new CrearAsesorDisciplinarRequest(
                    campoNombre.getText().toString().trim(),
                    campoApePat.getText().toString().trim(),
                    campoApeMat.getText().toString().trim(),
                    numeroCuenta,
                    campoContrasena.getText().toString().trim(),
                    campoCorreo.getText().toString().trim(),
                    campoNumCel.getText().toString().trim(),
                    materias,
                    horarios
                );

        repoAsesorDisciplinar.CrearAsesorDisciplinar(request, new AsesorDisciplinarRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    finish();
                    Toast.makeText(
                            crearAsesoresDisciplinares.this,
                            "ASESOR CREADO EXITOSAMENTE",
                            Toast.LENGTH_SHORT
                    ).show();


                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        crearAsesoresDisciplinares.this,
                        "hubo un error, intente de nuevo",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });




    }


}