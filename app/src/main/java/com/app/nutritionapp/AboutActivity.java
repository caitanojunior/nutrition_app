package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);
    }

    public void onOkPressed(View arg0) {
        Intent i = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(i);
    }

}
