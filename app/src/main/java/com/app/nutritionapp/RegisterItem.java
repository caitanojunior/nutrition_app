package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by caitano on 9/23/16.
 */

public class RegisterItem extends Activity {

    Repository r;
    Button save, search;
    EditText name, quantity, kcal;
    Spinner measure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_item);

        eventos();
    }

    // Inicialização dos componetes e Evento dos Botões
    public void eventos() {
        name = (EditText) findViewById(R.id.editTextName);
        quantity = (EditText) findViewById(R.id.editTextQuant);
        measure = (Spinner) findViewById(R.id.spinnerMeasure);
        kcal = (EditText) findViewById(R.id.editTextKcal);
        save = (Button) findViewById(R.id.ButtonRegister);
        search = (Button)findViewById(R.id.ButtonSearch);
        r = new Repository(this);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Food a = new Food();
                a.setName(name.getText().toString());
                double quantityDouble = Double.parseDouble(quantity.getText().toString());
                a.setQuantity(quantityDouble);
                a.setMeasure(measure.getSelectedItem().toString());
                r.insert(a);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                r.disAll();
                for(int i=0;i<r.cont;i++){
                    Toast.makeText(getApplicationContext(), "ID: "+r.id[i]+"\nNome: "+r.name[i]+"\nQuant: "+r.quantity[i]+"\nKcal: "+r.kcal[i], Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onBackPressed(View arg0) {
        Intent i = new Intent(RegisterItem.this, MainActivity.class);
        startActivity(i);

    }
}