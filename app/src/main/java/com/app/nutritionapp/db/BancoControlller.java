package com.app.nutritionapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fabricio on 11/16/16.
 */

public class BancoControlller {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private static final String TABLE = "foods";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String CALORIES = "calories";

    public BancoControlller(Context context){
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
}
