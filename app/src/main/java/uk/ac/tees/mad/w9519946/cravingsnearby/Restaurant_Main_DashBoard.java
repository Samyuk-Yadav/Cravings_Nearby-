package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Categories_List;
import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Foods_List;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_List_data;

public class Restaurant_Main_DashBoard extends AppCompatActivity {

    private RecyclerView caategory_List_RecyclerView;
    private RecyclerView food_List_RecyclerView;
    private RecyclerView.Adapter recycle_adapter;
    private RecyclerView.Adapter recycle_adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_main_dash_board);
        getSupportActionBar().hide();

        categories_List_Recycler_View();
        foods_List_Recycler_View();


    }

    private void categories_List_Recycler_View() {
        //Linear Layout Manager for aligning the data...
        LinearLayoutManager lm_layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        caategory_List_RecyclerView = findViewById(R.id.recyclerView_Categories);
        caategory_List_RecyclerView.setLayoutManager(lm_layoutManager);

        //ArrayList to form a pattern...
        ArrayList<Restaurant_List_data> restaurant_list_data = new ArrayList<>();
        restaurant_list_data.add(new Restaurant_List_data("Donut", "list_1"));
        restaurant_list_data.add(new Restaurant_List_data("Hotdog", "list_2"));
        restaurant_list_data.add(new Restaurant_List_data("Burger", "list_3"));
        restaurant_list_data.add(new Restaurant_List_data("Pizza", "list_4"));
        restaurant_list_data.add(new Restaurant_List_data("Drink", "list_5"));

        recycle_adapter = new Adapter_Categories_List(restaurant_list_data);
        caategory_List_RecyclerView.setAdapter(recycle_adapter);

    }

    private void foods_List_Recycler_View(){
        LinearLayoutManager fl_layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        food_List_RecyclerView = findViewById(R.id.recyclerView_Foods);
        food_List_RecyclerView.setLayoutManager(fl_layoutManager);
        ArrayList<Restaurant_Food_data> restaurant_food_data = new ArrayList<>();
        restaurant_food_data.add(new Restaurant_Food_data("Tikka Chicken, Olives, Fresh Oregano, Old Vintage Spices", "chicken_pizza_pic", 10.5, "Chicken Tikka Pizza"));
        restaurant_food_data.add(new Restaurant_Food_data("Ham or Beef, Lettuce, Onions, Spinach", "hot_dog_pic", 8.5, "Hot Dog"));
        restaurant_food_data.add(new Restaurant_Food_data("Extra Cheese, Beef or Ham, Tomato, Lettuce, Onions, Special Sauce","burger_pic",9.50,"Ham Burger"));
        restaurant_food_data.add(new Restaurant_Food_data("Olives, kalamata(Pitted), Oils(Vegitable,Olive), Fresh Oregano, Cherry Tomato, Basil", "veg_pizza_pic", 6.75, "Vegitable Pizza"));
        restaurant_food_data.add(new Restaurant_Food_data("Mozzerella Cheese, Slices Pepperoni, Ground Black Pepper, Fresh Oregano, Old Vintage Spices", "pizza_pic", 10.25, "Pepperoni Spicy Pizza"));

        recycle_adapter2 = new Adapter_Foods_List(restaurant_food_data);
        food_List_RecyclerView.setAdapter(recycle_adapter2);


    }


}