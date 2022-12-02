package uk.ac.tees.mad.w9519946.cravingsnearby.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Page;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.FragmentNewsBinding;

public class News extends Fragment {

    //Binding
    FragmentNewsBinding fragmentNewsBinding;
    PagerAdapter adapter_Pager_Adapter;


    public News() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}