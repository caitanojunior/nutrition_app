package com.app.nutritionapp.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fabricio on 11/16/16.
 */

public class CriaBanco extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final String TABLE = "foods";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String KCAL = "kcal";
    private static final int VERSION = 1;

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE_TABLE" +TABLE+"("
                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + "TEXT,"
                + QUANTITY + "INTEGER,"
                + UNIT + "TEXT,"
                + KCAL + "TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);
    }
}
