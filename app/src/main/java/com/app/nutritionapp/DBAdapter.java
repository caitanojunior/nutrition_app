package com.app.nutritionapp;

/**
 * Created by caitano on 9/23/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {


    public static final String KEY_ROWID = "_id"; // variavel que vai ser um componente da tabela
    public static final String KEY_NAME = "name"; // variavel que vai ser um componente da tabela
    public static final String KEY_QUANTITY = "quantity"; // variavel que vai ser um componente da tabela
    public static final String KEY_MEASURE = "measure"; // variavel que vai ser um componente da tabela
    public static final String KEY_KCAL = "kcal"; // variavel que vai ser um componente da tabela
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "alimentos"; // variavel que vai ser o name do banco
    private static final String DATABASE_TABLE = "alimento"; // variavel que vai ser o name da tabela
    private static final int DATABASE_VERSION = 1; // Versão do banco

    // String que vai ter Sql para criar a tabela
    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, "
                    + "name text not null, quantity double not null, measure text not null, kcal integer not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    // inicializando com o name do banco e a versão
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Criando a Tabela
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        //Se a tabela foi atualizada para uma nova versão
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            Log.w(TAG, "Update Version " + oldVersion
                    + " to "
                    + newVersion + ", New Data Base");
            db.execSQL("DROP TABLE IF EXISTS alimentos");
            onCreate(db);
        }
    }

    //Abre o Banco
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Fecha Banco
    public void close()
    {
        DBHelper.close();
    }

    //Método de Inserir
    public long insertTitle(String name, double quantity, String measure, int kcal){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_QUANTITY, quantity);
        initialValues.put(KEY_MEASURE, measure);
        initialValues.put(KEY_KCAL, kcal);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //Metodo de Deletar , Passando a tabela e o id
    public boolean deleteTitle(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID +
                "=" + rowId, null) > 0;
    }

    //Buscar Todos
    public Cursor getAll()
    {
        return db.query(DATABASE_TABLE, new String[] {
                        KEY_ROWID,
                        KEY_NAME,
                        KEY_QUANTITY,
                        KEY_MEASURE,
                        KEY_KCAL},
                null,null,null,null,null);
    }

    //Atualizando
    public boolean update(long rowId, String name,
                          double quantity, String measure, int kcal)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_QUANTITY, quantity);
        args.put(KEY_MEASURE, measure);
        args.put(KEY_KCAL, kcal);
        return db.update(DATABASE_TABLE, args,
                KEY_ROWID + "=" + rowId, null) > 0;
    }
}
