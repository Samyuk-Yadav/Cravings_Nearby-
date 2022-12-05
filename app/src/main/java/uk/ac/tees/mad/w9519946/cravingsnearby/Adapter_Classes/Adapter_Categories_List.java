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

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_List_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_Categories_List extends RecyclerView.Adapter<Adapter_Categories_List.Holder_View> {
    ArrayList<Restaurant_List_data> restaurantListData;

    public Adapter_Categories_List(ArrayList<Restaurant_List_data> restaurantListData) {
        this.restaurantListData = restaurantListData;
    }

    @NonNull
    @Override
    public Holder_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_holder, parent, false);
        return new Holder_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_View holder, int position1) {
        holder.name_Categories.setText(restaurantListData.get(position1).getTitles());
        String url_pic = "";
        switch (position1){
            case 0:{
                url_pic = "list_1";
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.list_background3));
                break;
            }
            case 1:{
                url_pic = "list_2";
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.list_background4));
                break;
            }
            case 2:{
                url_pic = "list_3";
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.list_background1));
                break;
            }
            case 3:{
                url_pic = "list_4";
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.list_background2));
                break;
            }
            case 4:{
                url_pic = "list_5";
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.list_background5));
                break;
            }
        }
        int resource_Id_Drawable = holder.itemView.getContext().getResources().getIdentifier(url_pic, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(resource_Id_Drawable).into(holder.picture_Categories);
    }

    @Override
    public int getItemCount() {
        return restaurantListData.size();
    }

    public class Holder_View extends RecyclerView.ViewHolder{
        //variables
        TextView name_Categories;
        ConstraintLayout constraintLayout;
        ImageView picture_Categories;
        public Holder_View(@NonNull View itemView) {
            super(itemView);
            //Hooks
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            picture_Categories = itemView.findViewById(R.id.image_View_pic);
            name_Categories = itemView.findViewById(R.id.text_View_title);



        }
    }
}
