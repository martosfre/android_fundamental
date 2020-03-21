package com.matoosfe.academium.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.matoosfe.academium.R;
import com.matoosfe.academium.control.EstudianteTrs;
import com.matoosfe.academium.modelo.Estudiante;

import java.util.Arrays;
import java.util.List;

public class ListaEstudianteActivity extends AppCompatActivity {

    private Estudiante estudianteRec;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiante);
        contexto = getApplicationContext();
        //Recuperar la lista
        final ListView lisVieEst = findViewById(R.id.lisVieEst);
        BottomNavigationView menNav = findViewById(R.id.menNavVie);
        menNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menEdiEst:
                        if(estudianteRec != null) {
                            Intent intRegEst = new Intent(getApplicationContext(), EstudianteActivity.class);
                            intRegEst.putExtra("estudiante", estudianteRec);
                            startActivity(intRegEst);
                        }else{
                            Toast.makeText(getApplicationContext(), "Se debe seleccionar un estudiante",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.menEliEst:
                        if(estudianteRec != null) {
                            final AlertDialog.Builder diaEliminarBuilder = new AlertDialog.Builder(ListaEstudianteActivity.this,
                                    R.style.Theme_AppCompat);
                            diaEliminarBuilder.setTitle("Confirmación Estudiante");
                            diaEliminarBuilder.setMessage("Está seguro de eliminar el estudiante?");
                            diaEliminarBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    EstudianteTrs adminEst = new EstudianteTrs();
                                    String mensaje = adminEst.eliminarEstudiante(estudianteRec);
                                    List<Estudiante> listaEstudiantes = adminEst.obtenerEstudiantes();
                                    //Creo un adaptador
                                    ArrayAdapter<Estudiante> estudianteArrayAdapter = new ArrayAdapter<Estudiante>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, listaEstudiantes);
                                    lisVieEst.setAdapter(estudianteArrayAdapter);
                                    Toast.makeText(getApplicationContext(), mensaje,
                                            Toast.LENGTH_LONG).show();

                                }
                            });
                            diaEliminarBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog dialogo = diaEliminarBuilder.create();
                            dialogo.show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Se debe seleccionar un estudiante",
                                    Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:

                }
                return false;
            }
        });


        //Recuperar estudiantes
        SharedPreferences preferences = getSharedPreferences("bdd", MODE_PRIVATE);
        String cadLisEst = preferences.getString("listaEstudiantes", null);
        //Convierte la cadena a una Lista
        Gson gson = new Gson();
        Estudiante [] arregloEstudiante = gson.fromJson(cadLisEst, Estudiante[].class);
        //Convertir a una lista
        List<Estudiante> listaEstudiantes = Arrays.asList(arregloEstudiante);
        //Creo un adaptador
        ArrayAdapter<Estudiante> estudianteArrayAdapter = new ArrayAdapter<Estudiante>(getApplicationContext(),
                android.R.layout.simple_list_item_1, listaEstudiantes);
        lisVieEst.setAdapter(estudianteArrayAdapter);
        //Método para seleccionar un elemento la lista
        lisVieEst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                estudianteRec = (Estudiante) adapterView.getItemAtPosition(position);
            }
        });
    }
}
