package com.example.note.guiavirtual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.note.guiavirtual.Equipos.AltaEquipo;
import com.example.note.guiavirtual.Equipos.ConsultaEquipo;
import com.example.note.guiavirtual.OTs.FragmentDefault;
import com.example.note.guiavirtual.OTs.FragmentListaOTs;
import com.example.note.guiavirtual.OTs.FragmentOTs;
import com.example.note.guiavirtual.OTs.MisOTs;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AltaEquipo.OnFragmentInteractionListener, ConsultaEquipo.OnFragmentInteractionListener , FragmentOTs.OnFragmentInteractionListener, FragmentListaOTs.OnFragmentInteractionListener{
        int AuxuliarUsuMain,x,AuxuliarRolMain;
        TextView textoRoll;
    Menu menu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // texto=(TextView)findViewById(R.id.textView19);
        //texto.setText(AuxuliarUsu);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       // textoRoll = (TextView) findViewById(R.id.nav_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecibirDatos();

        menu = navigationView.getMenu();
        //menu2=navigationView.getMenu();

        if (AuxuliarRolMain == 2) {
            MenuItem alta = menu.findItem(R.id.nav_AltaEquipos);
            MenuItem consulta = menu.findItem(R.id.nav_Cons);
            alta.setVisible(false);
            consulta.setVisible(false);
            Toast.makeText(getApplicationContext(),"Bienvenido Enfermero",Toast.LENGTH_LONG).show();


        } else if (AuxuliarRolMain == 3) {
            MenuItem alta = menu.findItem(R.id.nav_AltaEquipos);
            MenuItem consulta = menu.findItem(R.id.nav_Cons);
            alta.setVisible(false);
            consulta.setVisible(false);
            Toast.makeText(getApplicationContext(),"Bienvenido Doctor",Toast.LENGTH_LONG).show();
        } else if (AuxuliarRolMain == 0) {
            Toast.makeText(getApplicationContext(),"Bienvenido Aministrador",Toast.LENGTH_LONG).show();

        }else if (AuxuliarRolMain == 1) {
            Toast.makeText(getApplicationContext(),"Bienvenido Técnico",Toast.LENGTH_LONG).show();

        }
    }

    private void RecibirDatos() {
        Bundle extras = getIntent().getExtras();
        AuxuliarUsuMain=extras.getInt("Usuario");
        AuxuliarRolMain=extras.getInt("Rol");
        //Toast.makeText(getApplicationContext(),"Usuario: "+AuxuliarUsuMain + "   Rol: " + AuxuliarRolMain,Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_Guia) {
            // Aplicación de realidad aumentada

        } else if (id == R.id.nav_OTs) {
            //Bajas y modificaciones de OTs
            miFragment=new FragmentOTs();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_ListaOT) {
           //Listado de OTs pendientes

            miFragment=new FragmentListaOTs();
            fragmentSeleccionado=true;


        } else if (id == R.id.nav_AltaEquipos) {
            miFragment=new AltaEquipo();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_Cons) {
            //Consulta de equipos
            miFragment=new ConsultaEquipo();
            fragmentSeleccionado=true;
        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
