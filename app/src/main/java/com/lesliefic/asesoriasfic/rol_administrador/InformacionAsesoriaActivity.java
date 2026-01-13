package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.HorarioSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.ModalidadSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.RazonSpinnerAdapter;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.network.request.admin.CrearAsesoriaRequest;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesoriaRequest;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
import com.lesliefic.asesoriasfic.repositorios.AsesoriasCursoRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InformacionAsesoriaActivity extends AppCompatActivity {


    private Asesoria datosAsesoria;
    private EditText campoEstudiante, campoAsesor, campoNoAsesorias, etNota;
    Spinner spMateria, spRazon, spModalidad, spHorario;

    private Button btn_guardar;

    private List<Modalidad> listaModalidades = new ArrayList<>();
    private List<Razon> listaRazones = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();

    private MateriaSpinnerAdapter AdapSpMateria;
    private HorarioSpinnerAdapter AdapSpHorario;
    private ModalidadSpinnerAdapter AdapSpModalidad;
    private RazonSpinnerAdapter AdapSpRazon;

    AsesoriasCursoRepository repoAsesorias;

    TextView tvFecha;

    private ImageButton btn_regresar;

    private String fechaBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesoria);

        btn_regresar = findViewById(R.id.btnRegresar);
        btn_guardar = findViewById(R.id.btnConfirmar);

        repoAsesorias = new AsesoriasCursoRepository();

        datosAsesoria = (Asesoria) getIntent().getSerializableExtra("ASESORIA_INFO");
        listaMaterias = (ArrayList<Materia>) getIntent().getSerializableExtra("LISTA_MATERIAS");
        listaRazones = (ArrayList<Razon>) getIntent().getSerializableExtra("LISTA_RAZONES");
        listaModalidades = (ArrayList<Modalidad>) getIntent().getSerializableExtra("LISTA_MODALIDADES");
        listaHorarios = (ArrayList<Horario>) getIntent().getSerializableExtra("LISTA_HORARIOS");


        spMateria   = findViewById(R.id.spMateria);
        spRazon     = findViewById(R.id.spRazon);
        spModalidad = findViewById(R.id.spModalidad);
        spHorario   = findViewById(R.id.spHorario);

        AdapSpMateria = new MateriaSpinnerAdapter(this, listaMaterias);
        spMateria.setAdapter(AdapSpMateria);

        AdapSpHorario = new HorarioSpinnerAdapter(this, listaHorarios);
        spHorario.setAdapter(AdapSpHorario);

        AdapSpModalidad = new ModalidadSpinnerAdapter(this, listaModalidades);
        spModalidad.setAdapter(AdapSpModalidad);

        AdapSpRazon = new RazonSpinnerAdapter(this, listaRazones);
        spRazon.setAdapter(AdapSpRazon);

        campoEstudiante = findViewById(R.id.campoEstudiante);
        campoAsesor = findViewById(R.id.campoAsesor);
        campoNoAsesorias = findViewById(R.id.campoNoAsesorias);
        etNota = findViewById(R.id.etNota);

        tvFecha = findViewById(R.id.tvFecha);


        campoEstudiante.setText(datosAsesoria.getEstudiante());
        campoAsesor.setText(datosAsesoria.getAsesor());
        tvFecha.setText(datosAsesoria.getFechaInicio());
        fechaBackend = datosAsesoria.getFechaInicio();
        campoNoAsesorias.setText(String.valueOf(datosAsesoria.getSesionesTomadas()));
        etNota.setText(datosAsesoria.getObservaciones());

        seleccionarPorIdMateria(spMateria, datosAsesoria.getIdMateria());
        seleccionarPorIdRazon(spRazon, datosAsesoria.getIdRazon());
        seleccionarPorIdModalidad(spModalidad, datosAsesoria.getIdModalidad());
        seleccionarPorIdHorario(spHorario, datosAsesoria.getId_horario());


        btn_regresar.setOnClickListener(v -> finish());

        btn_guardar.setOnClickListener(v -> {
            editarAsesoria();
        });

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCalendario();
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

                        fechaBackend = fechaUI;
                    }
                }, año, mes, dia);

        datePickerDialog.show();
    }

    public void editarAsesoria(){
        Materia materia = (Materia) spMateria.getSelectedItem();
        Razon razon = (Razon) spRazon.getSelectedItem();
        Modalidad modalidad = (Modalidad) spModalidad.getSelectedItem();
        Horario horario = (Horario) spHorario.getSelectedItem();
        String sesiones_tomadas = campoNoAsesorias.getText().toString();
        String observaciones = etNota.getText().toString();
        int idMateria = materia.getId_materia();
        int idRazon = razon.getId_razon();
        int idModalidad = modalidad.getId_modalidad();
        int idHorario = horario.getId_horario();
        int sesionesTomadas = Integer.parseInt(sesiones_tomadas);


        EditarAsesoriaRequest request = new EditarAsesoriaRequest(
                datosAsesoria.getIdAsesoria(),
                idMateria,
                idModalidad,
                fechaBackend,
                idRazon,
                sesionesTomadas,
                observaciones,
                idHorario
        );

        repoAsesorias.editarAsesoria(request, new AsesorParRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    finish();
                    Toast.makeText(
                            getApplicationContext(),
                            "ASESORIA EDITADA",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error" + error,
                        Toast.LENGTH_SHORT
                ).show();
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


}