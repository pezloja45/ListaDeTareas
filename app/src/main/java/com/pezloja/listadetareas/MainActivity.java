package com.pezloja.listadetareas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_tareas = findViewById(R.id.rv_tareas);

        ControladorTarea.initArray();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager disposicion = new LinearLayoutManager(getApplicationContext());
            rv_tareas.setLayoutManager(disposicion);
        } else {
            GridLayoutManager disposicion = new GridLayoutManager(getApplicationContext(), 2);
            rv_tareas.setLayoutManager(disposicion);
        }

        setSupportActionBar(findViewById(R.id.menu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_añadir) {
            Intent intentAñadir = new Intent(getApplicationContext(), CrearTarea.class);
            startActivity(intentAñadir);
        } else if (item.getItemId() == R.id.menu_ordenar) {
            ControladorTarea.ordenarTareas();
            AdaptadorTareas adaptadorTareas = new AdaptadorTareas(ControladorTarea.getTareas());
            rv_tareas.setAdapter(adaptadorTareas);
        } else if (item.getItemId() == R.id.menu_salir) {
            confirmarCerrar();
        }
        return false;
    }

    private void confirmarCerrar() {
        new AlertDialog.Builder(this)
                .setTitle("Chapar")
                .setMessage("Estas seguro que quieres chapar el chiringo?")
                .setPositiveButton("Si", (dialogInterface, i) -> finish())
                .setNegativeButton("No", null)
                .create()
                .show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        AdaptadorTareas adaptadorTareas = new AdaptadorTareas(ControladorTarea.getTareas());
        rv_tareas.setAdapter(adaptadorTareas);
    }

}