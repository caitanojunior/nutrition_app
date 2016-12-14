package com.app.nutritionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;

import com.app.nutritionapp.db.ListData;
import com.app.nutritionapp.ui.MainFragment;


import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onItemClick(int position) {
        MainFragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 2:
                mFragment = new MainFragment();
                break;

            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private OnItemClickListener onClickBMI = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {

            if(position == 1){
                Intent it = new Intent(MainActivity.this, ImcActivity.class);
                startActivity(it);
                closeDrawer();
            } else {
                Intent it = new Intent(MainActivity.this, ImcActivity.class);
                startActivity(it);
                closeDrawer();
            }

        }
    };

    @Override
    public void onInt(Bundle savedInstanceState) {
        // User Information
        this.userName.setText("Rudson Lima");
        this.userEmail.setText("rudsonlive@gmail.com");
        this.userBackground.setImageResource(R.drawable.thrive_nutrition);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.option_imc), R.drawable.ic_bmi_calculator);
        mHelpLiveo.add(getString(R.string.options_food_list), R.drawable.plate_fork_knife);
        mHelpLiveo.add(getString(R.string.sent_mail), R.drawable.ic_send_black_24dp);
        mHelpLiveo.add(getString(R.string.drafts), R.drawable.ic_drafts_black_24dp);

        //with(this, Navigation.THEME_DARK). add theme dark
        //with(this, Navigation.THEME_LIGHT). add theme light

        with(this) // default theme is dark
                .startingPosition(2) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .footerItem(R.string.settings, R.drawable.ic_settings_black_24dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        onItemClick(position);
        this.setElevationToolBar(position != 2 ? 15 : 0);

    }
}
/*
    public void cad_item(View view) {
        Intent it = new Intent(MainActivity.this, RegisterItemActivity.class);
        startActivity(it);
    }

    public void imc(View view) {
        Intent it = new Intent(MainActivity.this, ImcActivity.class);
        startActivity(it);
    }

    public void food_list(View view) {
        Intent it = new Intent(MainActivity.this, ListData.class);
        startActivity(it);
    }

}*/
