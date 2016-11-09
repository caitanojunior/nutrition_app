package com.app.nutritionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

/**
 * Created by caitano on 10/22/16.
 */
public class ImcActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private boolean verifyEmptyFields(EditText text) {
        String value = text.getText().toString();
        if (value.matches("")) {
            Toast.makeText(this, R.string.invalid_value_imc_calc, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


    public void calcImc(View view) {

        EditText getWeight = (EditText) findViewById(R.id.edit_text_weight);
        EditText getHeightMt = (EditText) findViewById(R.id.edit_text_height);

        if(!verifyEmptyFields(getHeightMt) && !verifyEmptyFields(getWeight)) {

            int weight = Integer.parseInt(getWeight.getText().toString());
            float height_mt = Integer.parseInt(getHeightMt.getText().toString());

            //calculations
            float height_total = height_mt / 100;
            float height_square = height_total * height_total;

            float my_imc = weight / height_square;
            DecimalFormat decimal = new DecimalFormat("0.00");
            String result = decimal.format(my_imc);

            Context contexto = getApplicationContext();
            String resposta = null;

            if (my_imc < 16) {
                resposta = getString(R.string.very_underweight_bmi);
            } else if (my_imc <= 16.99) {
                resposta = getString(R.string.light_underweight_bmi);
            } else if (my_imc <= 18.49) {
                resposta = getString(R.string.underweight_bmi);
            } else if (my_imc <= 24.99) {
                resposta = getString(R.string.normal_bmi);
            } else if (my_imc <= 29.99) {
                resposta = getString(R.string.overweight_bmi);
            } else if (my_imc <= 34.99) {
                resposta = getString(R.string.obese_bmi_level1);
            } else if (my_imc <= 39.99) {
                resposta = getString(R.string.obese_bmi_level2);
            } else if (my_imc >= 40.00) {
                resposta = getString(R.string.obese_bmi_level3);
            }

            int duracao = Toast.LENGTH_LONG;
            String texto = "Seu IMC é: " + result + " " + resposta;
            Toast toast = Toast.makeText(contexto, texto, duracao);
            toast.show();

        }
    }


    public void onBackPressed(View arg0) {
        Intent i = new Intent(ImcActivity.this, MainActivity.class);
        startActivity(i);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Imc Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
