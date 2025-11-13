package com.lesliefic.asesoriasfic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

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
            iniciarNuevaActividad(asesoriasEnCursoActivity.class);
        }
        else if (itemid == R.id.nav_solicitudesPendientes){
            iniciarNuevaActividad(solicitudesPendientesActivity.class);
        }
        else if (itemid == R.id.nav_reportes){
            iniciarNuevaActividad(reportesActivity.class);
        }

    }

    private void iniciarNuevaActividad(Class<?> destinoactividad){
        startActivity(new Intent(this, destinoactividad));
        overridePendingTransition(0, 0);
    }
}