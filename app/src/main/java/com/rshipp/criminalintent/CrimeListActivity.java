package com.rshipp.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by george on 2/15/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}