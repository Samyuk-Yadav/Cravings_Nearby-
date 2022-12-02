package uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class News_API_Utis {
    //variables
    private static Retrofit news_FitRetero = null;

    public static News_Interface_API getNews_API_Interface(){
        if (news_FitRetero==null){
            news_FitRetero = new Retrofit.Builder().baseUrl(News_Interface_API.URL_Basic).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return news_FitRetero.create(News_Interface_API.class);
    }
}
