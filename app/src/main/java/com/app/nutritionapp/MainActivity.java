package com.app.nutritionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cad_item(View view){
        Intent it = new Intent(MainActivity.this, RegisterItem.class);
        startActivity(it);
    }

    public void exitApp(View view) {

        finishAffinity();
    }
}
