package com.fabriciooliveira.hbsisfab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class Pessoa implements Parcelable {
    private int id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("sobrenome")
    private String sobrenome;

    @SerializedName("dataNascimento")
    private String dataNascimento;

    public Pessoa() {

    }

    public Pessoa(String nome, String sobrenome, String dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(int id, String nome, String sobrenome, String dataNascimento, int ativo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(Parcel input) {
        this.id = input.readInt();
        this.nome = input.readString();
        this.sobrenome = input.readString();
        this.dataNascimento = input.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.sobrenome);
        dest.writeString(this.dataNascimento);
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {

        public Pessoa createFromParcel(Parcel in) {

            return new Pessoa(in);
        }

        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };
}
