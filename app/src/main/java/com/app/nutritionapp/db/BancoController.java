package com.app.nutritionapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fabricio on 11/16/16.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private static final String TABLE = "foods";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String CALORIES = "calories";

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public long insertValues(String name, int quantity, String unit, int calories) {
        ContentValues values = new ContentValues();
        long result;

        db = banco.getWritableDatabase();
        values.put(NAME, name);
        values.put(QUANTITY, quantity);
        values.put(UNIT, unit);
        values.put(CALORIES, calories);

        result = db.insert(TABLE, null, values);
        db.close();

        return result;

    }

    public Cursor loadingData(){
        Cursor cursor;
        String[] fields =  {banco.ID, banco.FOOD_NAME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE, fields, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
