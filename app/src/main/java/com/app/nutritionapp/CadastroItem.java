package com.app.nutritionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by caitano on 8/29/16.
 */
public class CadastroItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_item);
    }

    public void onBackPressed(View view) {
        finish();
    }
}
