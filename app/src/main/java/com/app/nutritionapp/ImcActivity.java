package com.app.nutritionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by caitano on 10/22/16.
 */
public class ImcActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc);
    }

    public void calcImc(View view){

        EditText getWeight = (EditText)findViewById(R.id.edit_text_weight);
        EditText getHeightMt = (EditText)findViewById(R.id.edit_text_height_mt);
        EditText getHeightCm = (EditText)findViewById(R.id.edit_text_height_cm);

        int weight = Integer.parseInt(getWeight.getText().toString());
        int height_mt = Integer.parseInt(getHeightMt.getText().toString());
        float height_cm = Integer.parseInt(getHeightCm.getText().toString());

        //calculations
        float heightBy100 = height_cm/100;
        float height_total = height_mt + heightBy100;
        float height_square = height_total * height_total;

        float my_imc = weight/height_square;
        DecimalFormat decimal = new DecimalFormat( "0.00" );
        String result = decimal.format(my_imc);

        Context contexto = getApplicationContext();
        String resposta = null;

        if(my_imc < 17){
            resposta = "Você está muito abaixo do peso!";
        }
        else if(my_imc <= 18.49 ){
            resposta = "Você está abaixo do peso!";
        }
        else if(my_imc <=24.99){
            resposta = "Você está no peso ideal!";
        }
        else if(my_imc <=29.99){
            resposta = "Atenção, você está acima do peso ideal!";
        }
        else if(my_imc <=34.99){
            resposta = "Cuidado, você está com obesidade grau I!";
        }
        else if(my_imc <=39.99){
            resposta = "Cuidado, você está com obesidade grau II (severa)!";
        }
        else if(my_imc >=40.00){
            resposta = "Cuidado, você está com Obesidade grau III (mórbida)!";
        }

        int duracao = Toast.LENGTH_LONG;
        String texto = "Seu IMC é: " + result + " " + resposta;
        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();
    }

    public void onBackPressed(View arg0) {
        Intent i = new Intent(ImcActivity.this, MainActivity.class);
        startActivity(i);
    }
}
