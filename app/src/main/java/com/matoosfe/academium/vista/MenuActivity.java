package com.matoosfe.academium.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matoosfe.academium.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * Método para llamar al menú
     * @param view
     */
    public void llamarMenu(View view){
        int idMen = view.getId();
        Intent intMen = null;
        if(idMen == R.id.btnRegEst){
            intMen = new Intent(getApplicationContext(),
                    EstudianteActivity.class);
        }else if(idMen == R.id.btnLisEst){
            intMen = new Intent(getApplicationContext(),
                    ListaEstudianteActivity.class);
        }else{
//            intMen = new Intent(getApplicationContext(),
//                    MapaEstudianteActivity.class);
        }
        startActivity(intMen);
    }
}
