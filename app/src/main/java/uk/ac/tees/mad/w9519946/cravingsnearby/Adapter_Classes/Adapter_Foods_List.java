package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

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

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull Holder_View holder, int position1) {
        holder.popular_title.setText(restaurantFoodData.get(position1).getFood_title());
        holder.popular_fees.setText(String.valueOf(restaurantFoodData.get(position1).getFood_fees()));

        int resource_Id_Drawable = holder.itemView.getContext().getResources().getIdentifier(restaurantFoodData.get(position1).getFood_pic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(resource_Id_Drawable).into(holder.picture_Foods_list);
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
