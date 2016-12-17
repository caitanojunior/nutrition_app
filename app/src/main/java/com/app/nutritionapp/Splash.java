package com.app.nutritionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Splash extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
