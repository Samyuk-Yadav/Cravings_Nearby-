package uk.ac.tees.mad.w9519946.cravingsnearby.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_News;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.News_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Top_News_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities.News_API_Utis;
import uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities.News_Interface_API;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Frag_Homie extends Fragment {


    String counting_Country = "gb";
    Adapter_News adapter_news;
    String sAPI_KEY_API = "dbf9455965134409a734d7d7721efdd2";
    ArrayList<News_data> news_dataArrayList;

    private RecyclerView homie_Recycler_View;

    public Frag_Homie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vitamin = inflater.inflate(R.layout.frag_homie, container, false);

        homie_Recycler_View = vitamin.findViewById(R.id.recycler_Home_View);
        news_dataArrayList = new ArrayList<>();
        homie_Recycler_View.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_news = new Adapter_News(getContext(), news_dataArrayList);
        homie_Recycler_View.setAdapter(adapter_news);

        news_Search_Find();

        return vitamin;
    }

    private void news_Search_Find() {
        News_API_Utis.getNews_API_Interface().getNews(counting_Country, 77, sAPI_KEY_API).enqueue(new Callback<Top_News_data>() {
            @Override
            public void onResponse(Call<Top_News_data> call, Response<Top_News_data> response) {
                if (response.isSuccessful()){
                    news_dataArrayList.addAll(response.body().getArticles());
                    adapter_news.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Top_News_data> call, Throwable t) {

            }
        });






    }
}