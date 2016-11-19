package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.nutritionapp.db.DBController;

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

        DBController crud = new DBController(getBaseContext());

        EditText getName = (EditText)findViewById(R.id.editTextName);
        EditText getQuantity = (EditText) findViewById(R.id.editTextQuant);
        Spinner mySpinner =  (Spinner) findViewById(R.id.spinnerMeasure);
        EditText getCalories = (EditText)findViewById(R.id.editTextKcal);

        String name = getName.getText().toString();
        String unit = mySpinner.getSelectedItem().toString();
        int response;

        int quantity = Integer.parseInt(getQuantity.getText().toString());
        int calories = Integer.parseInt(getCalories.getText().toString());
        long result;

        result = crud.insertValues(name, quantity, unit, calories);

        if (result == -1)
            response = R.string.error_insert;
        else {
            response = R.string.success_insert;
            getName.getText().clear();
            getQuantity.getText().clear();
            getCalories.getText().clear();
            mySpinner.getAdapter();
        }
        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
    }
}