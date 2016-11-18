package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.nutritionapp.db.BancoControlller;

/**
 * Created by caitano on 9/23/16.
 */

public class RegisterItem extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_item);
    }

    public void onBackPressed(View arg0) {
        Intent i = new Intent(RegisterItem.this, MainActivity.class);
        startActivity(i);
    }

    public void onRegisterFood(View view) {

        BancoControlller crud = new BancoControlller(getBaseContext());

        EditText getName = (EditText)findViewById(R.id.editTextName);
        EditText getQuantity = (EditText) findViewById(R.id.editTextQuant);
        Spinner mySpinner =  (Spinner) findViewById(R.id.spinnerMeasure);
        EditText getKCAL = (EditText)findViewById(R.id.editTextKcal);

        String name = getName.getText().toString();
        String unit = mySpinner.getSelectedItem().toString();
        String result;

        int quantity = Integer.parseInt(getQuantity.getText().toString());
        int kcal = Integer.parseInt(getKCAL.getText().toString());

        result = crud.insertValues(name, quantity, unit, kcal);

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}