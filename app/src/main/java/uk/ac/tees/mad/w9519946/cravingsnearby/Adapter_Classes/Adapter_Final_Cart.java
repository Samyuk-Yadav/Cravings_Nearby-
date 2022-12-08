package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper.Cart_Shopping;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities.Cart_Number_Of_Items;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_Final_Cart extends RecyclerView.Adapter<Adapter_Final_Cart.Holding_View> {

    private ArrayList<Restaurant_Food_data> restaurantFoodData;
    private Cart_Shopping cartShopping;
    private Cart_Number_Of_Items cart_number_of_items;

    public Adapter_Final_Cart(ArrayList<Restaurant_Food_data> restaurantFoodData, Context content, Cart_Number_Of_Items cart_number_of_items) {
        this.restaurantFoodData = restaurantFoodData;
        this.cartShopping = new Cart_Shopping(content);
        this.cart_number_of_items = cart_number_of_items;
    }

    @NonNull
    @Override
    public Holding_View onCreateViewHolder(@NonNull ViewGroup parent1, int viewType) {
        View v = LayoutInflater.from(parent1.getContext()).inflate(R.layout.final_cart_holderview, parent1, false);


        return new Holding_View(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holding_View hanger69, @SuppressLint("RecyclerView") int position691) {

        hanger69.food_Item_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartShopping.food_minus_btn(restaurantFoodData, position691, new Cart_Number_Of_Items() {
                    @Override
                    public void swaping() {
                        notifyDataSetChanged();
                        cart_number_of_items.swaping();
                    }
                });
            }
        });

        hanger69.food_Item_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartShopping.food_number_plus(restaurantFoodData, position691, new Cart_Number_Of_Items() {
                    @Override
                    public void swaping() {
                        notifyDataSetChanged();
                        cart_number_of_items.swaping();
                    }
                });

            }
        });

        hanger69.food_each_item_fees.setText(String.valueOf(restaurantFoodData.get(position691).getFood_fees()));
        hanger69.food_num.setText(String.valueOf(restaurantFoodData.get(position691).getCurrency_in_Cart()));
        hanger69.food_total_items_fees.setText(String.valueOf(Math.round((restaurantFoodData.get(position691).getCurrency_in_Cart() * restaurantFoodData.get(position691).getFood_fees()) * 100) / 100));
        hanger69.food_title.setText(restaurantFoodData.get(position691).getFood_title());

        int id_resources_of_Drawable = hanger69.itemView.getContext().getResources().getIdentifier(restaurantFoodData.get(position691).getFood_pic(), "drawable", hanger69.itemView.getContext().getPackageName());
        Glide.with(hanger69.itemView.getContext()).load(id_resources_of_Drawable).into(hanger69.food_item_pic);


    }

    @Override
    public int getItemCount() {
        return restaurantFoodData.size();
    }


    public class Holding_View extends RecyclerView.ViewHolder {
        TextView food_title;
        ImageView food_item_pic;
        TextView food_num;
        ImageView food_Item_Plus;
        ImageView food_Item_Minus;
        TextView food_each_item_fees;
        TextView food_total_items_fees;

        public Holding_View(@NonNull View i4temView) {
            super(i4temView);

            food_title = i4temView.findViewById(R.id.txtView_title);
            food_Item_Plus = i4temView.findViewById(R.id.food_Item_Plus);
            food_Item_Minus = i4temView.findViewById(R.id.food_Item_Minus);
            food_item_pic = i4temView.findViewById(R.id.food_item_Pic);
            food_num = i4temView.findViewById(R.id.food_item_number);
            food_total_items_fees = i4temView.findViewById(R.id.group_cost);
            food_each_item_fees = i4temView.findViewById(R.id.individual_cost);
        }
    }
}
