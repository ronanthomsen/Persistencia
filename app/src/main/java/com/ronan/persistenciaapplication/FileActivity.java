package com.ronan.persistenciaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    private EditText txtUsuarioFile;
    private EditText txtSenhaFile;
    private String usuario;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        txtUsuarioFile = (EditText) findViewById(R.id.txtUsuarioDb);
        txtSenhaFile = (EditText) findViewById(R.id.txtSenhaFile);
        usuario = txtUsuarioFile.getText().toString();
        senha = txtSenhaFile.getText().toString();
        String txt = null;

        FileInputStream fis = null;

        try{
            fis = openFileInput("applogin.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            txt = br.readLine();
            fis.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(txt != null){
            txtUsuarioFile.setText(txt.substring(0, txt.indexOf("|")));
            txtSenhaFile.setText(txt.substring(txt.indexOf("|")+1));
        }


    }


    public void mainFile(View v){

        CheckBox chbLembrar = (CheckBox) findViewById(R.id.chbLembrarDb);


        if(chbLembrar.isChecked()){

            FileOutputStream fos = null;
            try{
                fos = openFileOutput("applogin.txt", MODE_PRIVATE);
                String txt = txtUsuarioFile.getText().toString()+"|"+txtSenhaFile.getText().toString();
                fos.write(txt.getBytes());
                fos.close();

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();
        }


        finish();


    }

}
