package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Internet_View_News;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.News_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_News extends RecyclerView.Adapter<Adapter_News.Holder_View> {

    //Create Context
    Context textCon;
    ArrayList<News_data> news_dataArrayList;
    private Holder_View holder;
    private int position;

    //Constructor
    public Adapter_News(Context textCon, ArrayList<News_data> news_dataArrayList) {
        this.textCon = textCon;
        this.news_dataArrayList = news_dataArrayList;
    }

    @NonNull
    @Override
    public Adapter_News.Holder_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(textCon).inflate(R.layout.protocol_item, null, false);
       return new Holder_View(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_News.Holder_View holder, int position) {
        holder.news_Card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(textCon, Internet_View_News.class);
                inte.putExtra("url", news_dataArrayList.get(position).getUrl());
                textCon.startActivity(inte);
            }
        });

        holder.news_timee.setText("Published At: "+ news_dataArrayList.get(position).getPublishedAt());
        holder.news_authorr.setText(news_dataArrayList.get(position).getAuthor());
        holder.news_headd.setText(news_dataArrayList.get(position).getTitle());
        holder.news_contentt.setText(news_dataArrayList.get(position).getDescription());
        Glide.with(textCon).load(news_dataArrayList.get(position).getUrlToImage()).into(holder.news_imageVieww);

    }

    @Override
    public int getItemCount() {
        return news_dataArrayList.size();
    }

    public class Holder_View extends RecyclerView.ViewHolder {

        TextView news_authorr;
        CardView news_Card_view;
        TextView news_contentt;
        ImageView news_imageVieww;
        TextView news_timee;
        TextView news_headd;

        public Holder_View(@NonNull View view_Item) {
            super(view_Item);
            news_headd = view_Item.findViewById(R.id.main_heading);
            news_Card_view = view_Item.findViewById(R.id.v_ViewCard);
            news_contentt = view_Item.findViewById(R.id.text_description_news);
            news_imageVieww = view_Item.findViewById(R.id.selection_Image_news);
            news_timee = view_Item.findViewById(R.id.id_time_Samyuk);
            news_authorr = view_Item.findViewById(R.id.id_author_Samyuk);
        }
    }
}
