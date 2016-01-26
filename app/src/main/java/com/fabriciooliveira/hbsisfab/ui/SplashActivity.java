package com.fabriciooliveira.hbsisfab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fabriciooliveira.hbsisfab.AppController;
import com.fabriciooliveira.hbsisfab.R;
import com.fabriciooliveira.hbsisfab.businessobject.PessoaBO;
import com.fabriciooliveira.hbsisfab.model.Pessoa;
import com.fabriciooliveira.hbsisfab.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private ArrayList<Pessoa> mListPessoas = new ArrayList<Pessoa>();

    private PessoaBO mPessoaBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mPessoaBO = new PessoaBO(SplashActivity.this);

        requestURLForPeople();
    }

    public void requestURLForPeople() {

        JsonArrayRequest requestCategories = new JsonArrayRequest(Request.Method.GET, Constants.URL_FOR_PEOPLE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type fooType = new TypeToken<ArrayList<Pessoa>>() {}.getType();
                List<Pessoa> deserializedList = new Gson().fromJson(response.toString(), fooType);

                for (Pessoa p : deserializedList) {
                    mListPessoas.add(p);
                }

                mPessoaBO.deletarPessoasDoBanco();

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(Constants.PERSON_LIST, mListPessoas);
                startActivity(intent);

                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Erro: " + error.getMessage());

            }
        });

        AppController.getInstance().addToRequestQueue(requestCategories);
    }
}
