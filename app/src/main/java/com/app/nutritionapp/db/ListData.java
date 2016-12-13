package com.app.nutritionapp.db;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView;

import com.app.nutritionapp.R;

/**
 * Created by caitano on 11/18/16.
 */

public class ListData extends AppCompatActivity{

    private ListView listName, listQuant, listUnit, listCalories;
    Button del;
    DBController crud;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        crud = new DBController(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_data);

        final DBController crud = new DBController(getBaseContext());
        id = this.getIntent().getStringExtra("id");

        final Cursor cursor = crud.loadingData();


        String[] Fields = new String[] {CreateDB.FOOD_NAME, CreateDB.QUANTITY, CreateDB.UNIT, CreateDB.CALORIES};
        int[] idViews = new int[] {R.id.nameItem, R.id.quantItem, R.id.unitItem, R.id.caloriesItem};


        SimpleCursorAdapter adapterName = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data,cursor,Fields,idViews, 0);
        SimpleCursorAdapter adapterQuant = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data,cursor,Fields,idViews, 0);
        SimpleCursorAdapter adapterUnit = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data,cursor,Fields,idViews, 0);
        SimpleCursorAdapter adapterCalories = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data,cursor,Fields,idViews, 0);

        listName = (ListView)findViewById(R.id.listView);
        listName.setAdapter(adapterName);
        listQuant = (ListView)findViewById(R.id.listView);
        listQuant.setAdapter(adapterQuant);
        listUnit = (ListView)findViewById(R.id.listView);
        listUnit.setAdapter(adapterUnit);
        listCalories = (ListView)findViewById(R.id.listView);
        listCalories.setAdapter(adapterCalories);

        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));
                Intent intent = new Intent(ListData.this, UpdateData.class);
                intent.putExtra("id", codigo);
                startActivity(intent);
                finish();
            }
        });

        listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.buttonDelete).setVisibility(View.VISIBLE);
                return false;
            }
        });

        del = (Button) findViewById(R.id.ButtonDelete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deleteRegister(Integer.parseInt(id));
                Intent intent = new Intent(ListData.this, ListData.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
