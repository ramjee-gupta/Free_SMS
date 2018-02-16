package com.ram.freesms;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by RAMJEE on 06-02-2018.
 */

class SectionPagerAdapter extends FragmentPagerAdapter{

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                ContactsFragment contactsFragment = new ContactsFragment();
                return  contactsFragment;
            case 1:
                OutboxFragment outboxFragment = new OutboxFragment();
                return outboxFragment;
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "Contacts";
            case 1:
                return "Outbox";
            default:
                return null;
        }
    }
}
