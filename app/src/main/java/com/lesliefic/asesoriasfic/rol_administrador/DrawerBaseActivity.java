package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.login.loginActivity;
import com.lesliefic.asesoriasfic.rol_asesor.asesoriasCursoActivity;
import com.lesliefic.asesoriasfic.rol_asesor.historialAsesoriasActivity;
import com.lesliefic.asesoriasfic.rol_asesor.inicioAsesorActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.asesoriasCursoEstudianteActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.historialAsesoriasEstudianteActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.inicioEstudianteActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.solicitudesRevisionActivity;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        int rol = obtenerRol();

        if (rol == 1) {
            drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        } else if (rol == 2) {
            drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_asesor_drawer_base, null);
        } else if (rol == 3) {
            drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_estudiante_drawer_base, null);
        } else {
            // Por si algo raro pasa, carga el drawer de admin por default
            drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        }

        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_navigation_drower,
                R.string.close_navigation_drower
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        manejarAccionNavegacion(itemId);
        return true;
    }

    private void manejarAccionNavegacion(int itemid) {

        // ADMIN
        if (itemid == R.id.nav_asesoriasEnCurso) {
            iniciarNuevaActividad(AsesoriasEnCursoAdminActivity.class);

        } else if (itemid == R.id.nav_solicitudesPendientes) {
            iniciarNuevaActividad(solicitudesPendientesActivity.class);

        } else if (itemid == R.id.nav_reportes) {
            iniciarNuevaActividad(reportesActivity.class);

        } else if (itemid == R.id.asesoresDiciplnares) {
            iniciarNuevaActividad(asesoresDisciplinaresActivity.class);

        } else if (itemid == R.id.AsesoresPar) {
            iniciarNuevaActividad(ListaAsesoresActivity.class);

        } else if (itemid == R.id.Estudiantes) {
            iniciarNuevaActividad(estudiantesActivity.class);


            // ASESOR
        } else if (itemid == R.id.nav_solicitudesPendientesAsesor) {
            iniciarNuevaActividad(inicioAsesorActivity.class);

        } else if (itemid == R.id.nav_asesoriasEnCursoAsesor) {
            iniciarNuevaActividad(asesoriasCursoActivity.class);

        } else if (itemid == R.id.nav_historialAsesor) {
            iniciarNuevaActividad(historialAsesoriasActivity.class);


            // ESTUDIANTE
        } else if (itemid == R.id.nav_solicitarAsesoriasEstudiante) {
            iniciarNuevaActividad(inicioEstudianteActivity.class);

        } else if (itemid == R.id.nav_asesoriasEnCursoEstudiante) {
            iniciarNuevaActividad(asesoriasCursoEstudianteActivity.class);

        } else if (itemid == R.id.nav_solicitudesRevisionEstudiante) {
            iniciarNuevaActividad(solicitudesRevisionActivity.class);

        } else if (itemid == R.id.nav_historialAsesoriasEstudiante) {
            iniciarNuevaActividad(historialAsesoriasEstudianteActivity.class);


            // LOGOUT (para todos)
        } else if (itemid == R.id.cerrarSecion) {
            cerrarSesion();
        }
    }

    private void iniciarNuevaActividad(Class<?> destinoActividad) {
        if (this.getClass().equals(destinoActividad)) {
            return; // ya estás ahí
        }

        Intent intent = new Intent(this, destinoActividad);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    private void cerrarSesion() {
        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(this, loginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private int obtenerRol() {
        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        return prefs.getInt("ROL", 1);
    }



}