package com.app.nutritionapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBController {

    private static final String TABLE = "foods";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String CALORIES = "calories";
    private SQLiteDatabase db;
    private CreateDB banco;

    public DBController(Context context){
        banco = new CreateDB(context);
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
        String[] fields =  {banco.ID, banco.FOOD_NAME, banco.QUANTITY, banco.UNIT, banco.CALORIES};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE, fields, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor loadDataById(int id){
        Cursor cursor;
        String[] fields =  {banco.ID, banco.FOOD_NAME, banco.QUANTITY, banco.UNIT, banco.CALORIES};
        String where = CreateDB.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CreateDB.TABLE,fields,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateRegister(int id, String name, String quant, String unit, String kcal){
        ContentValues newValues;
        String where;

        db = banco.getWritableDatabase();

        where = CreateDB.ID + "=" + id;

        newValues = new ContentValues();
        newValues.put(CreateDB.FOOD_NAME, name);
        newValues.put(CreateDB.QUANTITY, quant);
        newValues.put(CreateDB.UNIT, unit);
        newValues.put(CreateDB.CALORIES, kcal);

        db.update(CreateDB.TABLE,newValues,where,null);
        db.close();
    }

    public void deleteRegister(long id){
        String where = CreateDB.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CreateDB.TABLE,where,null);
        db.close();
    }

}
