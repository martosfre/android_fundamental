package com.matoosfe.academium.vista;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.matoosfe.academium.R;
import com.matoosfe.academium.control.EstudianteTrs;
import com.matoosfe.academium.modelo.Estudiante;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase
 */
public class EstudianteDosActivity extends AppCompatActivity {

    private Spinner spiColEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_dos);

        spiColEst = findViewById(R.id.spiColEst);
        //Carga Valores Spinner
        //Crear una lista de valores
        ArrayList<String> colegios = new ArrayList<>();
        colegios.add("Spp-Cod");
        colegios.add("Mejía-Cod");
        colegios.add("Montúfar-Cod");
        //Crear un adaptador (Manejo los valores)
        ArrayAdapter<String> adaptador = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, colegios);
        //Setear el adaptador al combo
        spiColEst.setAdapter(adaptador);
    }

    /**
     * Método para guardar un estudiante
     * @param view
     */
    public void guardarEstudiante(View view) throws ParseException {
        //Recuperando los componentes
        EditText txtNomEst = findViewById(R.id.txtNomEst);
        EditText txtApeEst = findViewById(R.id.txtApeEst);
        EditText txtCorEst = findViewById(R.id.txtCorEst);
        EditText txtTelEst = findViewById(R.id.txtTelEst);
        EditText txtFecNacEst = findViewById(R.id.txtFecNacEst);


        //Llamo al controlador
        EstudianteTrs adminEstudiante = new EstudianteTrs();

        //Formato a la fecha
        SimpleDateFormat formatoFec = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacEst = formatoFec.parse(txtFecNacEst.getText().toString());

        //Creamos estudiante
        Estudiante estudiante = new Estudiante(1, txtNomEst.getText().toString(),
                txtApeEst.getText().toString(), txtCorEst.getText().toString(),
                txtTelEst.getText().toString(), fechaNacEst, spiColEst.getSelectedItem().toString());

        //Enviamos a guardar el estudiante
        String mensaje = adminEstudiante.guardarEstudiante(estudiante);

        //Almacenamos en memoria solo para este app
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        //Recuperamos el Editor
        SharedPreferences.Editor editor = preferences.edit();
        //Transformando el objeto a Cadena
        Gson gson = new Gson();
        String estudianteCadena = gson.toJson(estudiante);
        editor.putString("1", estudianteCadena);
        //Guardamos en memoria.
        editor.commit();

        Toast.makeText(getApplicationContext(), mensaje,
                Toast.LENGTH_SHORT).show();

        //Dirigirnos a la otra Pantalla
        Intent intLisEst = new Intent(getApplicationContext(), ListaEstudianteActivity.class);
        startActivity(intLisEst);
    }
}
