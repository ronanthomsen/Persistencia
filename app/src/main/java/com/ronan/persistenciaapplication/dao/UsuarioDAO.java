package com.ronan.persistenciaapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.ronan.persistenciaapplication.model.Usuario;

public class UsuarioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private Usuario criarUsuario(Cursor cursor){
        Usuario model = new Usuario(
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Usuarios._ID)),
            cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.LOGIN)),
            cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.SENHA)),
            cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.MARCADO))

        );

        return model;
    }

    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                            DatabaseHelper.Usuarios.COLUNAS, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DatabaseHelper.Usuarios.SENHA, usuario.getSenha());

        if(usuario.get_id() != null){
            return getDatabase().update(DatabaseHelper.Usuarios.TABELA, valores,
                    "_id = ?", new String[]{ usuario.get_id().toString() });
        }

        return getDatabase().insert(DatabaseHelper.Usuarios.TABELA, null, valores);
    }

    public boolean removerUsuario(int id){
        return getDatabase().delete(DatabaseHelper.Usuarios.TABELA,
                "_id = ?", new String[]{ Integer.toString(id) }) > 0;
    }

    public Usuario buscarUsuarioPorId(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "_id = ?", new String[]{ Integer.toString(id) }, null, null, null);

        if(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public boolean logar(String usuario, String senha){
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                null, "LOGIN = ? AND SENHA = ?", new String[]{usuario, senha}, null,null,null);

        if(cursor.moveToFirst()){
            return true;
        }

        return false;
    }

    public boolean marcadoLembrar(){

        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                new String[]{DatabaseHelper.Usuarios.MARCADO}, "_id = ?",new String[]{"0"}, null,null,null);

        String isMarcado = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.MARCADO));

        if(isMarcado.equals("true")){
            return true;
        }

        return false;
    }

    public String[] getCurrentLoginSenha(){

        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                null, "LOGIN = ? AND SENHA = ?", new String[]{"_id=1"}, null,null,null);
        String[] retorno = new String[1];

        retorno[0] = cursor.getString(cursor.getColumnIndex("LOGIN"));
        retorno[1] = cursor.getString(cursor.getColumnIndex("SENHA"));

        return retorno;


    }

    public boolean atualizarDb(String usuario, String senha, String marcado ){

        ContentValues cv = new ContentValues();
        ContentValues cv2 = new ContentValues();
        ContentValues cv3 = new ContentValues();

        int valida  = 0;
        int valida2 = 0;
        int valida3 = 0;

        cv.put(DatabaseHelper.Usuarios.LOGIN, usuario);
        cv2.put(DatabaseHelper.Usuarios.SENHA, senha);
        cv3.put(DatabaseHelper.Usuarios.MARCADO, marcado);


        valida = getDatabase().update(DatabaseHelper.Usuarios.TABELA,
                cv, "_id=1", null);

        valida2 = getDatabase().update(DatabaseHelper.Usuarios.TABELA,
                cv2, "_id=1", null);


        /*valida3 = getDatabase().update(DatabaseHelper.Usuarios.TABELA,
                cv3, "_id=1", null);*/

        //valida = (valida + valida2 + valida3)-2;

        if(valida != 0 && valida2 != 0){
            return true;
        }

        return false;
    }


    public void fechar(){
        databaseHelper.close();
        database = null;
    }
}
