package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Entertain;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Health;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Homie;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Sciences;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Sport;
import uk.ac.tees.mad.w9519946.cravingsnearby.Fragment.Frag_Tech;

public class Adapter_Page extends FragmentPagerAdapter {
    //Variables
    int counting_tab;

    //Adapter
    public Adapter_Page(@NonNull FragmentManager fragmentManager, int behave) {
        super(fragmentManager, behave);
        counting_tab = behave;
    }

    @NonNull
    @Override
    public Fragment getItem(int location) {
        switch (location){
            case 0:
                return new Frag_Homie();
            case 1:
                return new Frag_Sciences();
            case 2:
                return new Frag_Sport();
            case 3:
                return new Frag_Tech();
            case 4:
                return new Frag_Entertain();
            case 5:
                return new Frag_Health();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counting_tab;
    }
}
