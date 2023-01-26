package com.example.mad_1.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mad_1.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private final Context mContext;
    private int totalTabs;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        mContext = context;
        totalTabs = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                GetJokeFrag jokeFrag = new GetJokeFrag();
                return jokeFrag;
            case 1:
                GetUniversityFrag getUniversityFrag = new GetUniversityFrag();
                return getUniversityFrag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}