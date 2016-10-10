package com.app.nutritionapp;

/**
 * Created by caitano on 9/23/16.
 */

import android.content.Context;
import android.database.Cursor;

public class Repositorio {

    DBAdapter db;
    public static String id[] = new String[100];
    public static String name[] = new String[100];
    public static String quantity[] = new String[100];
    public static String measure[] = new String[100];
    public static String kcal[] = new String[100];
    public static int cont;

    public Repositorio(Context c){
        db = new DBAdapter(c);
    }

    public void update(Alimento a){
        db.open();
        db.update(a.getId(),
                a.getName(),
                a.getQuantity(),
                a.getMeasure(),
                a.getKcal());
            System.out.println("Updated");
        db.close();

    }
    public void insert(Alimento a){
        db.open();
        long id;
        id = db.insertTitle(
                a.getName(),
                a.getQuantity(),
                a.getMeasure(),
                a.getKcal());
        System.out.println("Put");
        db.close();

    }

    public void disAll(){

        cont = 0;
        db.open();
        try{
            Cursor c = db.getAll();
            if (c.moveToFirst())
            {
                do {

                    id[cont] =     c.getString(0);
                    name[cont] =     c.getString(1);
                    quantity[cont] =     c.getString(2);
                    measure[cont] =     c.getString(2);
                    kcal[cont] =     c.getString(2);
                    cont++;

                } while (c.moveToNext());
            }
        }catch(Exception e){
            System.out.println(e);
        }
        db.close();
    }


    public void del(Alimento a){
        db.open();
        db.deleteTitle(a.getId());
        System.out.println("Deleted");
        db.close();
    }
}
