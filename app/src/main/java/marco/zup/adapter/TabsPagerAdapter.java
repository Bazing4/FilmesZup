package marco.zup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Marco on 02-Dec-16.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();

    public void addFragments(Fragment fragments, String tabTitles){
        this.fragments.add(fragments);
        this.tabTitles.add(tabTitles);
    }

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // get item count - eigual ao numero de tabs
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
