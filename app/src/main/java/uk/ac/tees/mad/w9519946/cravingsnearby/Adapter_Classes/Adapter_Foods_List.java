package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Details_of_Food;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_Foods_List extends RecyclerView.Adapter<Adapter_Foods_List.Holder_View> {
    ArrayList<Restaurant_Food_data> restaurantFoodData;

    public Adapter_Foods_List(ArrayList<Restaurant_Food_data> restaurantListData) {
        this.restaurantFoodData = restaurantListData;
    }

    @NonNull
    @Override
    public Holder_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_view_holder, parent, false);
        return new Holder_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_View hanger, @SuppressLint("RecyclerView") int position1) {
        hanger.popular_title.setText(restaurantFoodData.get(position1).getFood_title());
        hanger.popular_fees.setText(String.valueOf(restaurantFoodData.get(position1).getFood_fees()));

        int resource_Id_Drawable = hanger.itemView.getContext().getResources().getIdentifier(restaurantFoodData.get(position1).getFood_pic(), "drawable", hanger.itemView.getContext().getPackageName());
        Glide.with(hanger.itemView.getContext()).load(resource_Id_Drawable).into(hanger.picture_Foods_list);

        hanger.popular_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(hanger.itemView.getContext(), Details_of_Food.class);
                inte.putExtra("restaurantFoodData", restaurantFoodData.get(position1));
                hanger.picture_Foods_list.getContext().startActivity(inte);
                //hanger.itemView.getContext().startActivity(inte);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantFoodData.size();
    }

    public class Holder_View extends RecyclerView.ViewHolder{
        //variables
        TextView popular_title;
        TextView popular_fees;
        TextView popular_add_btn;
        ImageView picture_Foods_list;
        public Holder_View(@NonNull View itemView) {
            super(itemView);
            //Hooks
            popular_title = itemView.findViewById(R.id.title1);
            popular_fees = itemView.findViewById(R.id.food_price);
            popular_add_btn = itemView.findViewById(R.id.add_btn);
            picture_Foods_list = itemView.findViewById(R.id.food_pic);



        }
    }
}
