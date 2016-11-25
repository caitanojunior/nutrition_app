package com.app.nutritionapp.db;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.app.nutritionapp.R;

/**
 * Created by caitano on 11/18/16.
 */

public class ListData extends AppCompatActivity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        DBController crud = new DBController(getBaseContext());
        Cursor cursor = crud.loadingData();

        String[] nameFields = new String[] {CreateDB.FOOD_NAME, CreateDB.QUANTITY, CreateDB.UNIT, CreateDB.CALORIES};
        int[] idViews = new int[] {R.id.nameItem, R.id.quantItem, R.id.unitItem, R.id.kcalItem};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,nameFields,idViews, 0);
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
