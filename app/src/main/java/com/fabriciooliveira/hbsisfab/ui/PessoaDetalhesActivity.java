package com.fabriciooliveira.hbsisfab.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fabriciooliveira.hbsisfab.R;
import com.fabriciooliveira.hbsisfab.model.Pessoa;
import com.fabriciooliveira.hbsisfab.util.Constants;
import com.fabriciooliveira.hbsisfab.util.DateHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class PessoaDetalhesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_details);

        TextView nome, sobrenome, dataNascimento, ativo;
        Pessoa pessoa;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nome = (TextView) findViewById(R.id.detalhe_nome);
        sobrenome = (TextView) findViewById(R.id.detalhe_sobrenome);
        dataNascimento = (TextView) findViewById(R.id.detalhe_datanascimento);
        ativo = (TextView) findViewById(R.id.detalhe_ativo);

        pessoa = getIntent().getParcelableExtra(Constants.PERSON);

        if (pessoa != null) {
            nome.setText(pessoa.getNome());
            sobrenome.setText(pessoa.getSobrenome());
            dataNascimento.setText(DateHelper.getLongDate(Long.valueOf(pessoa.getDataNascimento())));
            ativo.setText(pessoa.isAtivo() ? getString(R.string.ativo) : getString(R.string.inativo));
        }
    }

}
