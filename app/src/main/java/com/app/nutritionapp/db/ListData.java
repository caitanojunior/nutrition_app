package com.app.nutritionapp.db;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.app.nutritionapp.R;

/**
 * Created by caitano on 11/18/16.
 */

public class ListData extends Activity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.loadingData();

        String[] nameFields = new String[] {CriaBanco.ID, CriaBanco.FOOD_NAME};
        int[] idViews = new int[] {R.id.idFood, R.id.nameItem};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,nameFields,idViews, 0);
        list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
