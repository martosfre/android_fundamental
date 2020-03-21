package com.matoosfe.academium.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.matoosfe.academium.R;
import com.matoosfe.academium.control.UsuarioTrs;
import com.matoosfe.academium.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

    }

    /**
     * Método para validar el usuario
     */
    public void validarUsuario(View vista) {

        //1. Recuperando los componentes
        EditText txtNomUsu = findViewById(R.id.txtNomUsu);
        EditText txtClaUsu = findViewById(R.id.txtConUsu);

        //2. Llamar a la operación del controlador
        UsuarioTrs adminUsu = new UsuarioTrs();
        Usuario usuarioCon = adminUsu.verificarCredenciales(txtNomUsu.getText().toString(),
                txtClaUsu.getText().toString());
        if(usuarioCon != null){
            //Llamada a otro Formulario utilizando el controlador visual
            Intent intMen = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intMen);
        }else{
            Toast.makeText(getApplicationContext(), R.string.frmLogin_men_usuarioNoEncontrado,
                    Toast.LENGTH_LONG).show();
        }
    }
}
