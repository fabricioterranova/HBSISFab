package com.fabriciooliveira.hbsisfab.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.fabriciooliveira.hbsisfab.model.Pessoa;
import com.fabriciooliveira.hbsisfab.util.Constants;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class PessoaDB extends SQLiteOpenHelper {

    public PessoaDB(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_PESSOA ( ");
        query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("NOME TEXT(50) NOT NULL, ");
        query.append("SOBRENOME TEXT(50) NOT NULL, ");
        query.append("DATA_NASCIMENTO TEXT(50) ) ");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE TB_PESSOA ADD COLUMN ativo INTEGER DEFAULT 0");
        }
    }


    public void inserirListaDePessoas(ArrayList<Pessoa> listaDePessoas){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i <listaDePessoas.size(); i++) {
            Pessoa pessoa = listaDePessoas.get(i);

            values.put("NOME", pessoa.getNome());
            values.put("SOBRENOME", pessoa.getSobrenome());
            values.put("DATA_NASCIMENTO", pessoa.getDataNascimento());

            db.insert("TB_PESSOA", null, values);
        }
    }

    public ArrayList<Pessoa> recuperarListaDePessoas(){
        ArrayList<Pessoa> pessoaList = new ArrayList<Pessoa>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, "TB_PESSOA", null, null, null, null, null, "ID", null, null);

        while(cursor.moveToNext()){
            Pessoa pessoa = new Pessoa();

            pessoa.setNome(cursor.getString(1));
            pessoa.setSobrenome(cursor.getString(2));
            pessoa.setDataNascimento(cursor.getString(3));

            pessoaList.add(pessoa);
        }

        return pessoaList;
    }

    public void deletarDadosDoBanco() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("TB_PESSOA", null, null);
    }

}
