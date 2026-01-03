package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.Dialog;
import android.content.Intent;
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
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;

import java.util.ArrayList;
import java.util.List;

public class InformacionAsesoresParActivity extends AppCompatActivity {

    private AsesorPar datosAsesor;
    private ArrayList<Grupo> listaGrupos = new ArrayList<>();
    private ArrayList<Licenciatura> listaLicenciaturas = new ArrayList<>();

    RecyclerView rvMateriasAgregadas, rvHorariosAgregados;

    MateriaElegidaAdapter adapterMaterias;
    HorarioElegidoAdapter adapterHorariosSelect;
    HorarioAdapter adapterHorarios;

    GrupoSpinnerAdapter adapterGrupo;
    LicenciaturaSpinnerAdapter adapterLicenciatura;
    CatalogosRepository repoCatalogos;

    Button btn_agregar_materia, btn_agregar_horario, btn_guardar;
    Spinner spGrupo, spLicenciatura;

    private List<Materia> materiasElegidas = new ArrayList<>();
    private ArrayList<Materia> listaMaterias = new ArrayList<>();
    private List<Horario> horariosElegidos = new ArrayList<>();
    private ArrayList<Horario> listaHorarios = new ArrayList<>();





    EditText campoAsesorPar, campoNumeroCuenta, campoCorreo, campoNumCel, campoPromedio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesores_par);

        datosAsesor = (AsesorPar) getIntent().getSerializableExtra("ASESOR_INFO");
        listaGrupos = (ArrayList<Grupo>) getIntent().getSerializableExtra("LISTA_GRUPOS");
        listaLicenciaturas = (ArrayList<Licenciatura>) getIntent().getSerializableExtra("LISTA_LICENCIATURAS");

        if(datosAsesor == null){
            finish();
            return;
        }

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        repoCatalogos = new CatalogosRepository();

        spGrupo = findViewById(R.id.spGrupo);
        adapterGrupo = new GrupoSpinnerAdapter(this, listaGrupos);
        spGrupo.setAdapter(adapterGrupo);

        spLicenciatura = findViewById(R.id.spLicenciatura);
        adapterLicenciatura = new LicenciaturaSpinnerAdapter(this, listaLicenciaturas);
        spLicenciatura.setAdapter(adapterLicenciatura);



        campoAsesorPar = findViewById(R.id.campoAsesorPar);
        campoNumeroCuenta = findViewById(R.id.campoNumeroCuenta);
        campoCorreo = findViewById(R.id.campoCorreo);
        campoNumCel = findViewById(R.id.campoNumCel);
        campoPromedio = findViewById(R.id.campoPromedio);


        campoAsesorPar.setText(datosAsesor.getNombre_completo());
        campoNumeroCuenta.setText(datosAsesor.getNumeroCuenta());
        campoCorreo.setText(datosAsesor.getCorreo());
        campoNumCel.setText(datosAsesor.getNumeroTelefono());
        campoPromedio.setText(String.valueOf(datosAsesor.getPromedio()));

        btnRegresar.setOnClickListener(v -> {
            finish();
        });

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

        btn_agregar_materia = findViewById(R.id.btnAgregarMateria);
        btn_agregar_horario = findViewById(R.id.btnAgregarHorario);

        btn_agregar_materia.setOnClickListener(v -> {
            abrirDialogMaterias(listaMaterias);
        });

        btn_agregar_horario.setOnClickListener(v -> {
            abrirDialogHorario();
        });




        obtenerCatalagos();

        rvMateriasAgregadas = findViewById(R.id.rvMateriasAsesor);
        rvMateriasAgregadas.setLayoutManager(new LinearLayoutManager(this));
        adapterMaterias = new MateriaElegidaAdapter(materiasElegidas, clickListener1);
        rvMateriasAgregadas.setAdapter(adapterMaterias);

        adapterHorariosSelect = new HorarioElegidoAdapter(horariosElegidos, clickListener2);
        rvHorariosAgregados = findViewById(R.id.rvHorariosAsesor);
        rvHorariosAgregados.setLayoutManager(new LinearLayoutManager(this));
        rvHorariosAgregados.setAdapter(adapterHorariosSelect);

        List<Materia> m = datosAsesor.getMateriasAsesor();
        if(m != null){
            materiasElegidas.clear();
            materiasElegidas.addAll(m);
            adapterMaterias.notifyDataSetChanged();
        }

        List<Horario> h = datosAsesor.getHorariosAsesor();
        if(h != null){
            horariosElegidos.clear();
            horariosElegidos.addAll(h);
            adapterHorariosSelect.notifyDataSetChanged();
        }

        Log.d("MATERIAS", "MATERIAS: " + m);


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
                        InformacionAsesoresParActivity.this,
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
                        InformacionAsesoresParActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}