package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Page;

public class Heading_News extends AppCompatActivity {

    //variables
    TabItem science;
    TabItem health;
    String sAPI_KEY_API = "dbf9455965134409a734d7d7721efdd2";
    PagerAdapter adapter_pager;
    TabItem technology;
    ViewPager pager1_view;
    TabItem sports;
    TabItem entertain;
    Toolbar bar_tool;
    TabItem homeie;
    TabLayout t_tab_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding
        bar_tool = findViewById(R.id.top_toolbar1);
        setContentView(R.layout.activity_heading_news);
        getSupportActionBar().hide();
        //Hooks
        t_tab_layout = findViewById(R.id.tab_2);
        pager1_view = findViewById(R.id.fragment_container);
        homeie = findViewById(R.id.id_home);
        technology = findViewById(R.id.id_technology);
        health = findViewById(R.id.id_health);
        sports = findViewById(R.id.id_sports);
        entertain = findViewById(R.id.id_entertainment);
        science = findViewById(R.id.id_science);

        //Set Adapter
        adapter_pager = new Adapter_Page(getSupportFragmentManager(), 6);
        pager1_view.setAdapter(adapter_pager);

        //Tab Layout
        t_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabLayout) {
                pager1_view.setCurrentItem(tabLayout.getPosition());
                if (tabLayout.getPosition()==0||tabLayout.getPosition()==1||
                        tabLayout.getPosition()==2||tabLayout.getPosition()==3||
                        tabLayout.getPosition()==4||tabLayout.getPosition()==5){
                    adapter_pager.notifyDataSetChanged();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        pager1_view.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(t_tab_layout));




    }
}