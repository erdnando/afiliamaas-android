package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton btnTodas, btnNuevas, btnAceptadas, btnEnviadas, btnCanceladas, btnRechazadas;

    String usuario, password, empresa, idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnTodas = (ImageButton) findViewById(R.id.btnTodas);
        btnNuevas = (ImageButton) findViewById(R.id.btnNuevas);
        btnAceptadas = (ImageButton) findViewById(R.id.btnAceptadas);
        btnEnviadas = (ImageButton) findViewById(R.id.btnEnviadas);
        btnCanceladas = (ImageButton) findViewById(R.id.btnCanceladas);
        btnRechazadas = (ImageButton) findViewById(R.id.btnRechazadas);

        Intent intent = getIntent();

        usuario = intent.getStringExtra("usuario");
        password = intent.getStringExtra("password");
        empresa = intent.getStringExtra("empresa");
        idUsuario = intent.getStringExtra("idUsuario");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.MisSolicitudes) {

            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
            finish();
            startActivity(intent);

        } else if (id == R.id.NuevaSolicitud) {

            Intent intent = new Intent(getApplicationContext(), NuevaSolicitud.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", password);
            intent.putExtra("empresa", empresa);
            intent.putExtra("idUsuario", idUsuario);
            startActivity(intent);

        } else if (id == R.id.MisCitas) {

        } else if (id == R.id.Configuracion) {

        } else if (id == R.id.Salir) {

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void principal(View v) {

        Fragment fragment = null;

        switch (v.getId()) {

            case R.id.btnTodas:

                btnTodas.setImageResource(R.drawable.todas_2);
                btnNuevas.setImageResource(R.drawable.nuevas_1);
                btnAceptadas.setImageResource(R.drawable.aceptadas_1);
                btnEnviadas.setImageResource(R.drawable.enviadas_1);
                btnCanceladas.setImageResource(R.drawable.canceladas_1);
                btnRechazadas.setImageResource(R.drawable.rechazada_1);

                fragment = new FragmentTodas();

                break;

            case R.id.btnNuevas:

                btnTodas.setImageResource(R.drawable.todas_1);
                btnNuevas.setImageResource(R.drawable.nuevas_2);
                btnAceptadas.setImageResource(R.drawable.aceptadas_1);
                btnEnviadas.setImageResource(R.drawable.enviadas_1);
                btnCanceladas.setImageResource(R.drawable.canceladas_1);
                btnRechazadas.setImageResource(R.drawable.rechazada_1);

                fragment = new FragmentNuevas();

                break;

            case R.id.btnAceptadas:

                btnTodas.setImageResource(R.drawable.todas_1);
                btnNuevas.setImageResource(R.drawable.nuevas_1);
                btnAceptadas.setImageResource(R.drawable.aceptadas_2);
                btnEnviadas.setImageResource(R.drawable.enviadas_1);
                btnCanceladas.setImageResource(R.drawable.canceladas_1);
                btnRechazadas.setImageResource(R.drawable.rechazada_1);

                fragment = new FragmentAceptadas();

                break;

            case R.id.btnEnviadas:

                btnTodas.setImageResource(R.drawable.todas_1);
                btnNuevas.setImageResource(R.drawable.nuevas_1);
                btnAceptadas.setImageResource(R.drawable.aceptadas_1);
                btnEnviadas.setImageResource(R.drawable.enviadas_2);
                btnCanceladas.setImageResource(R.drawable.canceladas_1);
                btnRechazadas.setImageResource(R.drawable.rechazada_1);

                fragment = new FragmentEnviadas();

                break;

            case R.id.btnCanceladas:

                btnTodas.setImageResource(R.drawable.todas_1);
                btnNuevas.setImageResource(R.drawable.nuevas_1);
                btnAceptadas.setImageResource(R.drawable.aceptadas_1);
                btnEnviadas.setImageResource(R.drawable.enviadas_1);
                btnCanceladas.setImageResource(R.drawable.canceladas_2);
                btnRechazadas.setImageResource(R.drawable.rechazada_1);

                fragment = new FragmentCanceladas();

                break;

            case R.id.btnRechazadas:

                btnTodas.setImageResource(R.drawable.todas_1);
                btnNuevas.setImageResource(R.drawable.nuevas_1);
                btnAceptadas.setImageResource(R.drawable.aceptadas_1);
                btnEnviadas.setImageResource(R.drawable.enviadas_1);
                btnCanceladas.setImageResource(R.drawable.canceladas_1);
                btnRechazadas.setImageResource(R.drawable.rechazadas_2);

                fragment = new FragmentRechazadas();

                break;
        }

        if (fragment != null) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();
        }

    }

    public void toast(final String xml) {

        runOnUiThread(new Runnable() {
            public void run() {

                Toast.makeText(getApplicationContext(), xml, Toast.LENGTH_SHORT).show();

            }
        });
    }

}