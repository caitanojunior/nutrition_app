package com.app.nutritionapp.db;

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
    private ListView listName, listQuant, listUnit, listCalories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        DBController crud = new DBController(getBaseContext());
        Cursor cursor = crud.loadingData();

/*        String[] nameFields = new String[] {CreateDB.FOOD_NAME};
        int[] idNameViews = new int[] {R.id.nameItem};

        SimpleCursorAdapter adapterName = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,nameFields,idNameViews, 0);
        listName = (ListView)findViewById(R.id.listView_name);
        listName.setAdapter(adapterName);
/*//************************************************************************************
        String[] quantFields = new String[] {CreateDB.QUANTITY};
        int[] idQuantViews = new int[] {R.id.quantItem};

        SimpleCursorAdapter adapterQuant = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,quantFields,idQuantViews, 0);
        listQuant = (ListView)findViewById(R.id.listView_quant);
        listQuant.setAdapter(adapterQuant);
/*//***********************************************************************************
        String[] unitFields = new String[] {CreateDB.UNIT};
        int[] idUnitViews = new int[] {R.id.unitItem};

        SimpleCursorAdapter adapterUnit = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,unitFields,idUnitViews, 0);
        listUnit = (ListView)findViewById(R.id.listView_unit);
        listUnit.setAdapter(adapterUnit);*/
//************************************************************************************
        String[] kcalFields = new String[] {CreateDB.CALORIES};
        int[] idKcalViews = new int[] {R.id.caloriesItem};

        SimpleCursorAdapter adapterCalories = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_list_data,cursor,kcalFields,idKcalViews, 0);
        listCalories = (ListView)findViewById(R.id.listView_calories);
        listCalories.setAdapter(adapterCalories);
    }
}
