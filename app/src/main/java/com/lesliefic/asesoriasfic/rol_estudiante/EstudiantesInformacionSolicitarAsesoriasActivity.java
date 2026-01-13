package com.lesliefic.asesoriasfic.rol_estudiante;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.HorarioAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioElegidoAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaElegidaAdapter;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante; // ✅ CAMBIO
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;

import java.util.ArrayList;
import java.util.List;

public class EstudiantesInformacionSolicitarAsesoriasActivity extends AppCompatActivity {

    private AsesoresEstudiante datosAsesor; // ✅ CAMBIO

    private RecyclerView rvMateriasAgregadas, rvHorariosAgregados;

    private MateriaElegidaAdapter adapterMaterias;
    private HorarioElegidoAdapter adapterHorariosSelect;
    private HorarioAdapter adapterHorarios;

    private CatalogosRepository repoCatalogos;

    private final List<Materia> materiasElegidas = new ArrayList<>();
    private final ArrayList<Materia> listaMaterias = new ArrayList<>();

    private final List<Horario> horariosElegidos = new ArrayList<>();
    private final ArrayList<Horario> listaHorarios = new ArrayList<>();

    private EditText campoNombre, campoApePat, campoApeMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes_informacion_solicitar_asesorias);

        datosAsesor = (AsesoresEstudiante) getIntent().getSerializableExtra("ASESOR_INFO"); // ✅ CAMBIO
        if (datosAsesor == null) {
            finish();
            return;
        }

        repoCatalogos = new CatalogosRepository();

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> finish());

        campoNombre = findViewById(R.id.campoNombre);
        campoApePat = findViewById(R.id.campoApePat);
        campoApeMat = findViewById(R.id.campoApeMat);

        campoNombre.setText(datosAsesor.getNombre());
        campoApePat.setText(datosAsesor.getApellidoPaterno());
        campoApeMat.setText(datosAsesor.getApellidoMaterno());


        rvMateriasAgregadas = findViewById(R.id.rvMateriasAsesor);
        rvMateriasAgregadas.setLayoutManager(new LinearLayoutManager(this));

        MateriaElegidaAdapter.OnEliminarClickListener clickEliminarMateria = materia -> {
            int index = materiasElegidas.indexOf(materia);
            if (index != -1) {
                materiasElegidas.remove(index);
                adapterMaterias.notifyItemRemoved(index);
            }
        };

        adapterMaterias = new MateriaElegidaAdapter(materiasElegidas, clickEliminarMateria);
        rvMateriasAgregadas.setAdapter(adapterMaterias);


        rvHorariosAgregados = findViewById(R.id.rvHorariosAsesor);
        rvHorariosAgregados.setLayoutManager(new LinearLayoutManager(this));

        HorarioElegidoAdapter.OnEliminarClickListener clickEliminarHorario = horario -> {
            int index = horariosElegidos.indexOf(horario);
            if (index != -1) {
                horariosElegidos.remove(index);
                adapterHorariosSelect.notifyItemRemoved(index);
            }
        };

        adapterHorariosSelect = new HorarioElegidoAdapter(horariosElegidos, clickEliminarHorario);
        rvHorariosAgregados.setAdapter(adapterHorariosSelect);


        List<Materia> m = datosAsesor.getMateriasAsesor();
        if (m != null) {
            materiasElegidas.clear();
            materiasElegidas.addAll(m);
            adapterMaterias.notifyDataSetChanged();
        }

        List<Horario> h = datosAsesor.getHorariosAsesor();
        if (h != null) {
            horariosElegidos.clear();
            horariosElegidos.addAll(h);
            adapterHorariosSelect.notifyDataSetChanged();
        }

        Log.d("MATERIAS", "MATERIAS: " + m);

        obtenerCatalogos();
    }

    private void obtenerCatalogos() {
        // Materias
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                if (data != null) listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {

            }
        });

        // Horarios
        repoCatalogos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override
            public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                if (data != null) listaHorarios.addAll(data);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void abrirDialogMaterias(ArrayList<Materia> listaMaterias) {
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
        if (dialog.getWindow() != null) dialog.getWindow().setLayout(width, height);

        EditText et_buscar = dialog.findViewById(R.id.etBuscar);
        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String texto = s.toString().trim().toLowerCase();
                listaMateriasFiltrada.clear();

                if (!texto.isEmpty()) {
                    for (Materia m : listaMaterias) {
                        if (m.getMateria() != null && m.getMateria().trim().toLowerCase().contains(texto)) {
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

    private void abrirDialogHorario() {
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
        if (dialog.getWindow() != null) dialog.getWindow().setLayout(width, height);
    }

    private void seleccionarMateria(Materia materiaElegida) {
        materiasElegidas.add(materiaElegida);
        adapterMaterias.notifyDataSetChanged();
    }

    private void seleccionarHorario(Horario horarioElegido) {
        horariosElegidos.add(horarioElegido);
        adapterHorariosSelect.notifyDataSetChanged();
    }
}