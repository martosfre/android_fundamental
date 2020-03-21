package com.matoosfe.academium.vista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.matoosfe.academium.R;
import com.matoosfe.academium.control.EstudianteTrs;
import com.matoosfe.academium.modelo.Estudiante;
import com.matoosfe.academium.util.SpinnerUtil;
import com.matoosfe.academium.util.DatePickerFragment;
import com.matoosfe.academium.util.UtilImage;
import com.matoosfe.academium.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase
 */
public class EstudianteActivity extends AppCompatActivity {

    private ImageView imaVieEst;
    private EditText txtNomEst;
    private EditText txtApeEst;
    private EditText txtCorEst;
    private EditText txtTelEst;
    public EditText txtFecNacEst;
    private Spinner spiColEst;

    private Estudiante estudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        //Recuperando los componentes
        imaVieEst = findViewById(R.id.imgFotEst);
        txtFecNacEst = findViewById(R.id.txtFecNacEst);
        txtNomEst = findViewById(R.id.txtNomEst);
        txtApeEst = findViewById(R.id.txtApeEst);
        txtCorEst = findViewById(R.id.txtCorEst);
        txtTelEst = findViewById(R.id.txtTelEst);
        spiColEst = findViewById(R.id.spiColEst);

        //Cargar Foto
        imaVieEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        //Recuperar valores del Intent
        Intent intLisEst = getIntent();
        estudiante = intLisEst.getParcelableExtra("estudiante");
        if(estudiante != null){
            //Poblar los valores
            txtNomEst.setText(estudiante.getNombreEst());
            txtApeEst.setText(estudiante.getApellidoEst());
            txtCorEst.setText(estudiante.getCorreoEst());
            txtTelEst.setText(estudiante.getTelefonoEst());
            txtFecNacEst.setText(new SimpleDateFormat("yyyy-MM-dd").format(estudiante.getFechaNacEst()));
            //Colegio
            int posSpi = SpinnerUtil.getPositionByElement((BaseAdapter) spiColEst.getAdapter(),
                    estudiante.getColegioEst());
            spiColEst.setSelection(posSpi);

            //Imagen
            imaVieEst.getLayoutParams().width = 150;
            imaVieEst.getLayoutParams().height = 150;

            Bitmap bitmap = BitmapFactory.decodeByteArray(estudiante.getImagenEst(), 0,
                    estudiante.getImagenEst().length);
            imaVieEst.setImageBitmap(bitmap);

        }
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
    public void guardarEstudiante(View view) {
        try {


            //Llamo al controlador
            EstudianteTrs adminEstudiante = new EstudianteTrs();

            //Formato a la fecha
            SimpleDateFormat formatoFec = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacEst = null;
            try {
                fechaNacEst = formatoFec.parse(txtFecNacEst.getText().toString());
            } catch (ParseException e) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1980, 4, 27);
                fechaNacEst = calendar.getTime();
            }

            //Creamos estudiante o actualizamos
            String mensaje = null;
            Estudiante estudianteTmp = new Estudiante(adminEstudiante.generarSecuencia(), txtNomEst.getText().toString(),
                    txtApeEst.getText().toString(), txtCorEst.getText().toString(),
                    txtTelEst.getText().toString(), fechaNacEst, spiColEst.getSelectedItem().toString(), null);

            //Verificar Estudiante
            verificarCampos(estudianteTmp);

            if(estudiante != null){
                estudiante = new Estudiante(estudiante.getCodEst(), txtNomEst.getText().toString(),
                        txtApeEst.getText().toString(), txtCorEst.getText().toString(),
                        txtTelEst.getText().toString(), fechaNacEst, spiColEst.getSelectedItem().toString(),
                        UtilImage.convertirABytes(imaVieEst));
                //Enviamos a guardar el estudiante
                mensaje = adminEstudiante.actualizarEstudiante(estudiante);
            }else{
                estudiante = new Estudiante(adminEstudiante.generarSecuencia(), txtNomEst.getText().toString(),
                        txtApeEst.getText().toString(), txtCorEst.getText().toString(),
                        txtTelEst.getText().toString(), fechaNacEst, spiColEst.getSelectedItem().toString(),
                        UtilImage.convertirABytes(imaVieEst));
                //Enviamos a guardar el estudiante
                mensaje = adminEstudiante.guardarEstudiante(estudiante);
            }


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
            Intent intLisEst = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intLisEst);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se pudo guardar el estudiante:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
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

    /**
     * Método para verificar los campos en un formulario
     * @param estudiante
     * @throws Exception
     */
    private void verificarCampos(Estudiante estudiante) throws Exception {
        //1. Validar Requeridos
        if(estudiante.getNombreEst().equals("")){
            txtNomEst.setError("Nombre requerido");
            txtNomEst.setFocusable(true);
        }

        if(estudiante.getApellidoEst().equals("")){
            txtApeEst.setError("Apellido requerido");
            txtApeEst.setFocusable(true);
        }

        if(estudiante.getCorreoEst().equals("")){
            txtCorEst.setError("Correo requerido");
            txtCorEst.setFocusable(true);
        }

        if(estudiante.getTelefonoEst().equals("")){
            txtTelEst.setError("Teléfono requerido");
            txtTelEst.setFocusable(true);
        }

        //2. Validar Formatos
        if(Validator.validarCorreo(estudiante.getCorreoEst()) == false){
            throw new Exception("Correo incorrecto");
        }

        if(!Validator.validarNumeroTelefonico(estudiante.getTelefonoEst())){
            throw new Exception("Teléfono incorrecto");
        }

        if(!Validator.validarFechaNacimiento(estudiante.getFechaNacEst())){
            throw new Exception("Fecha de Nacimiento incorrecta");
        }
    }

}


