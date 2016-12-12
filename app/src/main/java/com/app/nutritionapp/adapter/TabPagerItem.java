package com.app.nutritionapp.adapter;

import android.support.v4.app.Fragment;

/**
 * Created by fabriciolelis on 12/12/16.
 */

public class TabPagerItem {
    private final CharSequence mTitle;
    private final Fragment mFragment;

    public TabPagerItem(CharSequence title, Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
