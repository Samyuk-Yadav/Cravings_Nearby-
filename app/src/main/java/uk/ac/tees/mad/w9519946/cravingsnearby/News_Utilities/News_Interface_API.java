package uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Top_News_data;

public interface News_Interface_API {

    String URL_Basic = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<Top_News_data> getNews(
        @Query("country") String country,
        @Query("pageSize") int pagesize,
        @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<Top_News_data> getCatrgoricalNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apiKey
    );

}
