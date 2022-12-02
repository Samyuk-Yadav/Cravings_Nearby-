package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Calls;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Chats;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Homie;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.News;
import uk.ac.tees.mad.w9519946.cravingsnearby.Heading_News;

public class Adapter_Fragments extends FragmentPagerAdapter {

    public Adapter_Fragments(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Chats();
            case 1: return new News();
            case 2: return new Calls();
            default: return new Chats();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String heading = null;
        if (position==0){
            heading = "CHATS";
        }
        if (position==1){
            heading = "NEWS";
        }
        if (position==2){
            heading = "CALLS";
        }
        return heading;
    }
}
