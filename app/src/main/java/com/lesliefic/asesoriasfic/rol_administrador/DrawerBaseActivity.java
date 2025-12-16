package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.login.loginActivity;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_navigation_drower, R.string.close_navigation_drower);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        manejaraccionnavegacion(menuItem.getItemId());
        return false;
    }

    private void manejaraccionnavegacion (int itemid){
        if (itemid == R.id.nav_asesoriasEnCurso){
            iniciarNuevaActividad(AsesoriasEnCursoActivity.class);
        }
        else if (itemid == R.id.nav_solicitudesPendientes){
            iniciarNuevaActividad(solicitudesPendientesActivity.class);
        }
        else if (itemid == R.id.nav_reportes){
            iniciarNuevaActividad(reportesActivity.class);
        }
        else if(itemid == R.id.asesoresDiciplnares){
            iniciarNuevaActividad(asesoresDisciplinaresActivity.class);
        }
        else if (itemid == R.id.AsesoresPar) {
            iniciarNuevaActividad(ListaAsesoresActivity.class);
        }
        else if (itemid == R.id.Estudiantes){
            iniciarNuevaActividad(estudiantesActivity.class);
        }
        else if (itemid == R.id.cerrarSecion){
            iniciarNuevaActividad(loginActivity.class);
        }

    }

    private void iniciarNuevaActividad(Class<?> destinoactividad){
        startActivity(new Intent(this, destinoactividad));
        overridePendingTransition(0, 0);
    }
}