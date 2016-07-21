package com.ronan.persistenciaapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class SharedPreferencesActivity extends AppCompatActivity {


    private EditText txtUsuario;
    private EditText txtSenha;
    private String usuario;
    private String senha;

    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        txtUsuario = (EditText) findViewById(R.id.txtUsuarioDb);
        txtSenha = (EditText)  findViewById(R.id.txtSenha);
        usuario = txtUsuario.getText().toString();
        senha = txtSenha.getText().toString();

        sp = getPreferences(MODE_PRIVATE);

        usuario = sp.getString("sp_usuario", null);
        senha = sp.getString("sp_senha", null);

        if(usuario != null){
            txtUsuario.setText(usuario);
            txtSenha.setText(senha);
        }

    }



public void mainSharedPreferences(View v){

   // EditText _txtUsuario = (EditText) findViewById(R.id.txtUsuarioDb);
    //EditText _txtSenha = (EditText)  findViewById(R.id.txtSenha);
    CheckBox _chbLembrar = (CheckBox)  findViewById(R.id.chbLembrarDb);

    String _usuario = txtUsuario.getText().toString();
    String _senha = txtSenha.getText().toString();

    SharedPreferences.Editor e = sp.edit();


    if( !_usuario.equals("") && !_senha.equals("") ){

        if(_chbLembrar.isChecked()){

            e.putString("sp_usuario",_usuario);
            e.putString("sp_senha",_senha);
            e.commit();
            finish();
        }

    }

    finish();




}







}


