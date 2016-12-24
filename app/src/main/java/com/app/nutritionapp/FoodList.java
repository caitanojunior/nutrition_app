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
    private ListView  listQuant, listUnit, listCalories;
    DBController crud;
    private ListView listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_data);

        crud = new DBController(getBaseContext());
        final Cursor cursor = crud.loadingData();


        String[] Fields = new String[]{CreateDB.FOOD_NAME, CreateDB.QUANTITY, CreateDB.UNIT, CreateDB.CALORIES};
        int[] idViews = new int[]{R.id.nameItem, R.id.quantItem, R.id.unitItem, R.id.caloriesItem};


        SimpleCursorAdapter adapterName = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterQuant = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterUnit = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data, cursor, Fields, idViews, 0);
        SimpleCursorAdapter adapterCalories = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_data, cursor, Fields, idViews, 0);

        listName = (ListView) findViewById(R.id.listView);
        listName.setAdapter(adapterName);
        ListView listQuant = (ListView) findViewById(R.id.listView);
        listQuant.setAdapter(adapterQuant);
        ListView listUnit = (ListView) findViewById(R.id.listView);
        listUnit.setAdapter(adapterUnit);
        ListView listCalories = (ListView) findViewById(R.id.listView);
        listCalories.setAdapter(adapterCalories);
        registerForContextMenu(listName);

        listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));*/
                return false;
            }
        });

       /* listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, final long id) {

                view.findViewById(R.id.buttonDelete2).setVisibility(View.VISIBLE);
                del = (Button) findViewById(R.id.buttonDelete2);
                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        crud.deleteRegister(id);
                        Intent intent = new Intent(ListData.this, ListData.class);
                        startActivity(intent);
                        finish();
                    }
                });
                return false;
            }
        });

        /*listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID));
                Intent intent = new Intent(FoodList.this, UpdateFoodRegister.class);
                intent.putExtra("id", codigo);
                startActivity(intent);
                finish();
            }
        }); */
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
                //String codigo;
                //cursor.moveToPosition(position);
                intent = new Intent(FoodList.this, UpdateFoodRegister.class);
                intent.putExtra("id", info.id);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
