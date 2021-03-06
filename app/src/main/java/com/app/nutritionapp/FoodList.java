package com.app.nutritionapp;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.app.nutritionapp.db.CreateDB;
import com.app.nutritionapp.db.DBController;

public class FoodList extends AppCompatActivity {
    private ListView  listName, listQuant, listUnit, listCalories;
    DBController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_food_list);

        crud = new DBController(getBaseContext());
        final Cursor cursor = crud.loadingData();


        String[] Fields = new String[]{CreateDB.FOOD_NAME, CreateDB.QUANTITY, CreateDB.UNIT, CreateDB.CALORIES};
        int[] idViews = new int[]{R.id.nameItem, R.id.quantItem, R.id.unitItem, R.id.caloriesItem};


        SimpleCursorAdapter adapterName = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_food_list, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterQuant = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_food_list, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterUnit = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_food_list, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterCalories = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_food_list, cursor, Fields, idViews, 0);

        listName = (ListView) findViewById(R.id.listView);
        listName.setAdapter(adapterName);
        listQuant = (ListView) findViewById(R.id.listView);
        listQuant.setAdapter(adapterQuant);
        listUnit = (ListView) findViewById(R.id.listView);
        listUnit.setAdapter(adapterUnit);
        listCalories = (ListView) findViewById(R.id.listView);
        listCalories.setAdapter(adapterCalories);
        registerForContextMenu(listName);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent;

        switch (item.getItemId()) {
            case R.id.context_menu_delete:
                crud.deleteRegister(info.id);
                intent = new Intent(FoodList.this, FoodList.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.context_menu_update:
                Cursor cursor = crud.loadingData();
                String codigo;
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));
                intent = new Intent(FoodList.this, UpdateFoodRegister.class);
                intent.putExtra("id", String.valueOf(info.id));
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
