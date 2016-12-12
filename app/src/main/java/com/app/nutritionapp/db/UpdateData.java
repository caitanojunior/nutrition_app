package com.app.nutritionapp.db;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.nutritionapp.MainActivity;
import com.app.nutritionapp.R;
import com.app.nutritionapp.RegisterItemActivity;

/**
 * Created by caitano on 12/9/16.
 */

public class UpdateData extends Activity {
    EditText name;
    EditText quantity;
    Spinner unit;
    EditText kcal;
    Button update;
    Cursor cursor;
    DBController crud;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_item);

        id = this.getIntent().getStringExtra("id");

        crud = new DBController(getBaseContext());

        name = (EditText)findViewById(R.id.editTextName);
        quantity = (EditText)findViewById(R.id.editTextQuant);
        unit = (Spinner) findViewById(R.id.spinnerMeasure);
        kcal = (EditText)findViewById(R.id.editTextKcal);


        update = (Button)findViewById(R.id.ButtonUpdate);

        cursor = crud.loadDataById(Integer.parseInt(id));
        name.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.FOOD_NAME)));
        quantity.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.QUANTITY)));
        unit.setTag(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.UNIT)));
        kcal.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.CALORIES)));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.updateRegister(Integer.parseInt(id), name.getText().toString(),quantity.getText().toString(),
                        unit.getSelectedItem().toString(), kcal.getText().toString());
                Intent intent = new Intent(UpdateData.this,ListData.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressedListFood(View arg0) {
        Intent i = new Intent(UpdateData.this, ListData.class);
        startActivity(i);
    }
}
