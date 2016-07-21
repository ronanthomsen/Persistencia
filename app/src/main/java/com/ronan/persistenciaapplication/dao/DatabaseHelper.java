package com.ronan.persistenciaapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tasks";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuários
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                    +"login text not null, senha text not null, marcado text)");

        //Cadastrar um usuário
        db.execSQL("insert into usuarios(login, senha) values('admin', '123', '0')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String MARCADO = "marcado";

        public static final String[] COLUNAS = new String[]{
            _ID, LOGIN, SENHA, MARCADO
        };
    }

}
