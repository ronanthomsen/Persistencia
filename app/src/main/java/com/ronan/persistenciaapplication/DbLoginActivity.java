package com.ronan.persistenciaapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ronan.persistenciaapplication.dao.UsuarioDAO;
import com.ronan.persistenciaapplication.model.Usuario;

import java.util.List;


public class DbLoginActivity extends AppCompatActivity {

    private EditText txtUsuarioDb;
    private EditText txtSenhaDb;
    private UsuarioDAO helper;
    private CheckBox chbLembrarDb;
    private List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_login);

        txtUsuarioDb = (EditText) findViewById(R.id.txtUsuarioDb);
        txtSenhaDb = (EditText) findViewById(R.id.txtSenhaDb);
        chbLembrarDb = (CheckBox) findViewById(R.id.chbLembrarDb);
        helper = new UsuarioDAO(this);

        /*if(helper.marcadoLembrar()){

            String lembrar[]  = new String[1];
            lembrar = helper.getCurrentLoginSenha();
            txtUsuarioDb.setText(lembrar[0]);
            txtSenhaDb.setText(lembrar[1]);

        }*/

    }



    public void recuperaLogin(){



    }


    public void loginDb(View v){

        String usuario = txtUsuarioDb.getText().toString();
        String senha = txtSenhaDb.getText().toString();
        String marcado = "0";

        //usuarios = helper.listarUsuarios();



        boolean validacao = true;

        if(usuario == null || usuario.equals(""))
        {
            validacao = false;
            Toast.makeText(this,"Usuario ou Senha em branco", Toast.LENGTH_SHORT).show();
        }

        if(senha == null || senha.equals(""))
        {
            validacao = false;
            Toast.makeText(this,"Usuario ou Senha em branco", Toast.LENGTH_SHORT).show();
        }

        if(validacao){

            if(helper.logar(usuario,senha)){


                /*if(chbLembrarDb.isChecked()){
                    marcado = "1";
                    if(helper.atualizarDb(usuario, senha, marcado)){
                        Toast.makeText(this,"Login correto", Toast.LENGTH_LONG).show();
                    }



                }else if(!chbLembrarDb.isChecked()){

                   if(helper.atualizarDb(usuario, senha, marcado)){
                       Toast.makeText(this,"Login correto", Toast.LENGTH_LONG).show();
                   }
                }*/


                Toast.makeText(this,"Login correto", Toast.LENGTH_LONG).show();

            }else{

                Toast.makeText(this,"ERRO", Toast.LENGTH_LONG).show();

            }

        }



    }

    public void atualizaDb(View v){

        String usuario = txtUsuarioDb.getText().toString();
        String senha = txtSenhaDb.getText().toString();
        String lembrar = new String();
        lembrar = "false";

        if(chbLembrarDb.isChecked())
        {
            lembrar = "true";
        }




        if(helper.atualizarDb(usuario, senha, lembrar)){

            Toast.makeText(this,"Ok", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Não atualizado", Toast.LENGTH_LONG).show();
        }



    }

}
