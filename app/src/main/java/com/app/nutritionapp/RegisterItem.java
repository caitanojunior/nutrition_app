package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by caitano on 9/23/16.
 */

public class RegisterItem extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_item);
    }

    public void onBackPressed(View arg0) {
        Intent i = new Intent(RegisterItem.this, MainActivity.class);
        startActivity(i);
    }
}