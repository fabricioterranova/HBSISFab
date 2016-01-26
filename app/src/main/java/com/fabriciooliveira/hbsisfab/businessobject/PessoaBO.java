package com.fabriciooliveira.hbsisfab.businessobject;

import android.content.Context;


import com.fabriciooliveira.hbsisfab.database.PessoaDB;
import com.fabriciooliveira.hbsisfab.model.Pessoa;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class PessoaBO {
    private PessoaDB mPessoaDB;

    public PessoaBO(Context context) {
        mPessoaDB = new PessoaDB(context);
    }

    public void salvarListaDePessoas(ArrayList<Pessoa> listaPessoas){
        mPessoaDB.inserirListaDePessoas(listaPessoas);
    }

    public ArrayList<Pessoa> recuperarListaDePessoas() {
        return mPessoaDB.recuperarListaDePessoas();
    }

    public void deletarPessoasDoBanco() {
        mPessoaDB.deletarDadosDoBanco();
    }

}
