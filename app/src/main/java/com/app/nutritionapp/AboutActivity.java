package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by caitano on 10/29/16.
 */
public class AboutActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    public void onOkPressed(View arg0) {
        Intent i = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(i);
    }

}
