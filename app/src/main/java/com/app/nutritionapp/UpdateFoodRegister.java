package com.app.nutritionapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.nutritionapp.db.CreateDB;
import com.app.nutritionapp.db.DBController;

public class UpdateFoodRegister extends AppCompatActivity {

    EditText name;
    EditText quantity;
    Spinner unit;
    EditText kcal;
    Button update;
    Cursor cursor;
    DBController crud;
    String id;
    Button del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update__food_register);

        id = this.getIntent().getStringExtra("id");

        crud = new DBController(getBaseContext());

        name = (EditText) findViewById(R.id.editTextName);
        quantity = (EditText) findViewById(R.id.editTextQuant);
        unit = (Spinner) findViewById(R.id.spinnerMeasure);
        kcal = (EditText) findViewById(R.id.editTextKcal);


        update = (Button) findViewById(R.id.ButtonUpdate);

        cursor = crud.loadDataById(Integer.parseInt(id));
        name.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.FOOD_NAME)));
        quantity.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.QUANTITY)));
        unit.setTag(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.UNIT)));
        kcal.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.CALORIES)));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.updateRegister(Integer.parseInt(id), name.getText().toString(), quantity.getText().toString(),
                        unit.getSelectedItem().toString(), kcal.getText().toString());

                Intent intent = new Intent(UpdateFoodRegister.this, FoodList.class);
                startActivity(intent);
                finish();
            }
        });

        del = (Button) findViewById(R.id.ButtonDelete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deleteRegister(Integer.parseInt(id));
                Intent intent = new Intent(UpdateFoodRegister.this, FoodList.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void onBackPressedListFood(View arg0) {
        Intent i = new Intent(UpdateFoodRegister.this, FoodList.class);
        startActivity(i);
    }

}
