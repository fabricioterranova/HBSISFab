package com.fabriciooliveira.hbsisfab.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.fabriciooliveira.hbsisfab.R;
import com.fabriciooliveira.hbsisfab.businessobject.PessoaBO;
import com.fabriciooliveira.hbsisfab.model.Pessoa;
import com.fabriciooliveira.hbsisfab.ui.PessoaDetalhesActivity;
import com.fabriciooliveira.hbsisfab.util.Constants;
import com.fabriciooliveira.hbsisfab.util.DateHelper;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.ViewHolderPessoa>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static ArrayList<Pessoa> mListPessoas = new ArrayList<Pessoa>();
    private PessoaBO mPessoaBO;


    public PessoaAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mPessoaBO = new PessoaBO(mContext);
        mListPessoas = mPessoaBO.recuperarListaDePessoas();
    }

    public void setmListPessoas(ArrayList<Pessoa> mListPessoas) {
        this.mListPessoas = mListPessoas;

        notifyDataSetChanged();
    }

    @Override
    public ViewHolderPessoa onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.pessoa_celula, parent, false);
        ViewHolderPessoa viewHolderPayment = new ViewHolderPessoa(view);

        return viewHolderPayment;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPessoa holder, final int position) {
        final Pessoa pessoaAtual = mListPessoas.get(position);

        holder.mNome.setText(pessoaAtual.getNome());

        long dataNascimento = Long.parseLong(pessoaAtual.getDataNascimento());
        holder.mNascimento.setText(DateHelper.formatarDataLonga(dataNascimento));

        holder.mCheckBox.setChecked(pessoaAtual.isAtivo());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pessoaAtual.setAtivo(true);
                    mListPessoas.set(position, pessoaAtual);
                } else {
                    pessoaAtual.setAtivo(false);
                    mListPessoas.set(position, pessoaAtual);
                }
            }
        });

        holder.mBotaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPessoaBO.atualizarPessoaPorID(mListPessoas.get(position));

                Intent intent = new Intent(mContext, PessoaDetalhesActivity.class);
                intent.putExtra(Constants.PERSON, pessoaAtual);
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mListPessoas.size();
    }

    static class ViewHolderPessoa extends RecyclerView.ViewHolder{

        private TextView mNome;
        private TextView mNascimento;
        private CheckBox mCheckBox;
        private Button mBotaoSalvar;

        public ViewHolderPessoa(View itemView) {
            super(itemView);

            mNome = (TextView) itemView.findViewById(R.id.pessoa_nome);
            mNascimento = (TextView) itemView.findViewById(R.id.pessoa_nascimento);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_ativo);
            mBotaoSalvar = (Button) itemView.findViewById(R.id.botao_salvar);
        }

    }
}
