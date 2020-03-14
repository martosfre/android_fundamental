package com.matoosfe.academium.vista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.matoosfe.academium.R;
import com.matoosfe.academium.control.EstudianteTrs;
import com.matoosfe.academium.modelo.Estudiante;
import com.matoosfe.academium.util.DatePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase
 */
public class EstudianteActivity extends AppCompatActivity {

    private ImageView imaVieEst;
    public static EditText txtFecNacEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        imaVieEst = findViewById(R.id.imgFotEst);
        txtFecNacEst = findViewById(R.id.txtFecNacEst);

        //Cargar Foto
        imaVieEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
    }

    /**
     * Método para cargar la imagen
     */
    public void cargarImagen(){
        //Intent Imagen
        Intent intIma = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intIma, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1  && data != null){
            Uri imagenSel = data.getData();
            String [] pathImagen = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imagenSel, pathImagen,
                    null, null, null);
            cursor.moveToFirst();

            int indiceCol = cursor.getColumnIndex(pathImagen[0]);
            String pathImaRec = cursor.getString(indiceCol);
            cursor.close();

            //imaVieEst.setImageBitmap(BitmapFactory.decodeFile(pathImaRec));
            imaVieEst.setImageURI(imagenSel);
        }
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
        Spinner spiColEst = findViewById(R.id.spiColEst);

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

        //Recuperamos la lista de estudiantes para guardar en Memoria
        List<Estudiante> estudiantes = adminEstudiante.obtenerEstudiantes();

        //Almacenamos en memoria solo para este app
        SharedPreferences preferences = getSharedPreferences("bdd",MODE_PRIVATE);

        //Recuperamos el Editor
        SharedPreferences.Editor editor = preferences.edit();

        //Transformando el objeto a Cadena
        Gson gson = new Gson();
        String estudianteCadena = gson.toJson(estudiantes);
        editor.putString("listaEstudiantes", estudianteCadena);

        //Guardamos en memoria.
        editor.commit();

        Toast.makeText(getApplicationContext(), mensaje,
                Toast.LENGTH_SHORT).show();

        //Dirigirnos a la otra Pantalla
        Intent intLisEst = new Intent(getApplicationContext(), ListaEstudianteActivity.class);
        startActivity(intLisEst);
    }

    /**
     * Método para mostrar el calendario
     * @param v
     */
    public void mostrarCalendario(View v) {
        DatePickerFragment dialogoFecha = DatePickerFragment
                .newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String fechaSeleccionada = year + "-" + (month + 1) + "-" + day;
                txtFecNacEst.setText(fechaSeleccionada);
            }
        });

        dialogoFecha.show(getSupportFragmentManager(), "datePicker");
    }

}


