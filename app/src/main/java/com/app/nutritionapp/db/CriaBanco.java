package com.app.nutritionapp.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fabricio on 11/16/16.
 */

public class CriaBanco extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foods_database.db";
    private static final String TABLE = "foods";
    private static final String ID = "_id";
    private static final String FOOD_NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String CALORIES = "calories";
    private static final int VERSION = 2;

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FOOD_NAME + " TEXT,"
                + QUANTITY + " INTEGER,"
                + UNIT + " TEXT, "
                + CALORIES + " INTEGER" + ")";
        db.execSQL(CREATE_FOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
