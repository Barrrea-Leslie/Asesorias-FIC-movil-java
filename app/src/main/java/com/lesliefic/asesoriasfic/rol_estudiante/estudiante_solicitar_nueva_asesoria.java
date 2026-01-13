package com.lesliefic.asesoriasfic.rol_estudiante;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoresElegirAdapter;
import com.lesliefic.asesoriasfic.adaptador.EstudianteAutoCompleteAdapter;
import com.lesliefic.asesoriasfic.adaptador.EstudianteElegirAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.ModalidadSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.RazonSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.SolicitarAsesoriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminCrearAsesoriaBinding;
import com.lesliefic.asesoriasfic.modelo.Asesores;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.CrearSolicitudPendienteRequest;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
import com.lesliefic.asesoriasfic.repositorios.AsesoriasCursoRepository;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;
import com.lesliefic.asesoriasfic.repositorios.SolicitarAsesoriaRepository;
import com.lesliefic.asesoriasfic.repositorios.SolicitudesRepository;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class estudiante_solicitar_nueva_asesoria extends DrawerBaseActivity {

    private ImageButton btn_regresar;
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private List<Asesores> listaAsesores = new ArrayList<>();
    private EstudiantesRepository repoEstudiantes;
    private AsesoriasCursoRepository repoAsesorias;

    private AsesoresEstudiante datosAsesorSolicitado;

    private SolicitudesRepository repoSolicitudes;
    private SolicitarAsesoriaRepository repoSolicitar;
    private ActivityAdminCrearAsesoriaBinding activityAdminCrearAsesoriaBinding;

    private List<Grupo> listaGrupos = new ArrayList<>();
    private List<Licenciatura> listaLicenciaturas = new ArrayList<>();
    private List<Modalidad> listaModalidades = new ArrayList<>();
    private List<Razon> listaRazones = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Horario> listaHorariosAsesor = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();
    private List<Materia> listaMateriasAsesor = new ArrayList<>();

    EstudianteAutoCompleteAdapter adapterEstudiantes;

    EstudianteElegirAdapter adapterElegirEst;
    AsesoresElegirAdapter adapterElegirAse;

    private TextView tvFecha;

    Spinner spMateria, spHorario, spModalidad, spRazon, spGrupo, spLicenciatura;


    private MateriaSpinnerAdapter AdapSpMateria;
    private HorarioSpinnerAdapter AdapSpHorario;
    private ModalidadSpinnerAdapter AdapSpModalidad;
    private RazonSpinnerAdapter AdapSpRazon;

    EditText campoEstudiante, campoAsesor, etNota;
    Button btn_agregar_estudiante, btn_agregar_asesor, btn_crear;




    private int id_estudiante_elegido;
    private int id_licenciatura_estudiante;
    private int id_asesor_elegido;
    private String fechaBackend;

    int getId_estudiante;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitar_nueva_asesoria);




        repoEstudiantes = new EstudiantesRepository();
        repoAsesorias = new AsesoriasCursoRepository();
        repoSolicitudes = new SolicitudesRepository();
        repoSolicitar = new SolicitarAsesoriaRepository();
        btn_regresar = findViewById(R.id.btnRegresar);
        btn_agregar_asesor = findViewById(R.id.btnAgregarAsesor);
        btn_crear = findViewById(R.id.btnGuardar);
        tvFecha = findViewById(R.id.tvFecha);
        etNota = findViewById(R.id.etNota);

        if (listaMaterias == null) listaMaterias = new ArrayList<>();
        if (listaRazones == null) listaRazones = new ArrayList<>();
        if (listaModalidades == null) listaModalidades = new ArrayList<>();
        if (listaHorarios == null) listaHorarios = new ArrayList<>();

        listaMaterias = (ArrayList<Materia>) getIntent().getSerializableExtra("LISTA_MATERIAS");
        listaRazones = (ArrayList<Razon>) getIntent().getSerializableExtra("LISTA_RAZONES");
        listaModalidades = (ArrayList<Modalidad>) getIntent().getSerializableExtra("LISTA_MODALIDADES");
        listaHorarios = (ArrayList<Horario>) getIntent().getSerializableExtra("LISTA_HORARIOS");
        datosAsesorSolicitado = (AsesoresEstudiante) getIntent().getSerializableExtra("ASESOR_INFO");

        campoAsesor = findViewById(R.id.campoAsesor);

        if(datosAsesorSolicitado != null){
            id_asesor_elegido = datosAsesorSolicitado.getIdAsesor();
            campoAsesor.setText(datosAsesorSolicitado.getNombre_completo());
        }



        tvFecha = findViewById(R.id.tvFecha);

        spMateria = findViewById(R.id.spMateria);
        spHorario = findViewById(R.id.spHorario);
        spModalidad = findViewById(R.id.spModalidad);
        spRazon = findViewById(R.id.spRazon);


        AdapSpMateria = new MateriaSpinnerAdapter(this, listaMaterias);
        spMateria.setAdapter(AdapSpMateria);

        AdapSpHorario = new HorarioSpinnerAdapter(this, listaHorarios);
        spHorario.setAdapter(AdapSpHorario);

        AdapSpModalidad = new ModalidadSpinnerAdapter(this, listaModalidades);
        spModalidad.setAdapter(AdapSpModalidad);

        AdapSpRazon = new RazonSpinnerAdapter(this, listaRazones);
        spRazon.setAdapter(AdapSpRazon);

        AdapSpMateria.notifyDataSetChanged();
        AdapSpRazon.notifyDataSetChanged();
        AdapSpModalidad.notifyDataSetChanged();
        AdapSpHorario.notifyDataSetChanged();





        btn_regresar.setOnClickListener(v -> {
            finish();
        });



        btn_agregar_asesor.setOnClickListener(view -> {
            abrirDialogAsesor();
        });

        btn_crear.setOnClickListener(v -> {
            crearSolicitud();
        });

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCalendario();
            }
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

    public void crearSolicitud(){

        getId_estudiante = obtenerIdUsuario();

        Materia materia = (Materia) spMateria.getSelectedItem();
        Razon razon = (Razon) spRazon.getSelectedItem();
        Modalidad modalidad = (Modalidad) spModalidad.getSelectedItem();
        Horario horario = (Horario) spHorario.getSelectedItem();


        int idMateria = materia.getId_materia();
        int idRazon = razon.getId_razon();
        int idModalidad = modalidad.getId_modalidad();
        int idHorario = horario.getId_horario();


        CrearSolicitudPendienteRequest request = new CrearSolicitudPendienteRequest(
                idMateria,
                idHorario,
                idModalidad,
                getId_estudiante,
                id_asesor_elegido,
                fechaBackend,
                idRazon,
                etNota.getText().toString()

        );

        repoSolicitudes.crearSolicitud(request, new SolicitudesRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    finish();
                    Toast.makeText(
                            getApplicationContext(),
                            "SOLICITUD CREADA",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void mostrarCalendario() {
        // Obtener la fecha actual para mostrarla por defecto en el selector
        final Calendar c = Calendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int mes = monthOfYear + 1;

                        // UI: dd/MM/yyyy
                        String fechaUI = String.format("%02d/%02d/%04d", dayOfMonth, mes, year);
                        tvFecha.setText(fechaUI);

                        // BACKEND: yyyy-MM-dd
                        fechaBackend = String.format("%04d-%02d-%02d", year, mes, dayOfMonth);
                    }
                }, año, mes, dia);

        datePickerDialog.show();
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
            id_licenciatura_estudiante = estudiante.getIdLicenciatura();
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

    private void seleccionarPorIdMateria(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Materia m = (Materia) spinner.getAdapter().getItem(i);
            if (m != null && m.getId_materia() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private void seleccionarPorIdRazon(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Razon r = (Razon) spinner.getAdapter().getItem(i);
            if (r != null && r.getId_razon() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private void seleccionarPorIdModalidad(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Modalidad m = (Modalidad) spinner.getAdapter().getItem(i);
            if (m != null && m.getId_modalidad() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private void seleccionarPorIdHorario(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Horario h = (Horario) spinner.getAdapter().getItem(i);
            if (h != null && h.getId_horario() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private int obtenerIdUsuario() {
        SharedPreferences prefs = getSharedPreferences("id_usuario", MODE_PRIVATE);
        return prefs.getInt("ID_USUARIO", 1);
    }


}