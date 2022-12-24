package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Categories_List;
import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Foods_List;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_List_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantMainDashBoardBinding;

public class Restaurant_Main_DashBoard extends AppCompatActivity {

    private RecyclerView caategory_List_RecyclerView;
    private RecyclerView food_List_RecyclerView;
    private RecyclerView.Adapter recycle_adapter;
    private RecyclerView.Adapter recycle_adapter2;

    ActivityRestaurantMainDashBoardBinding activityRestaurantMainDashBoardBinding;
    
    ImageView photo;
    TextView user_name;
    ImageView back_arrow_btn;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantMainDashBoardBinding = ActivityRestaurantMainDashBoardBinding.inflate(getLayoutInflater());
        setContentView(activityRestaurantMainDashBoardBinding.getRoot());
        getSupportActionBar().hide();

        //Set Hooks and Connect Firebase..
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        photo = findViewById(R.id.imageView_restaurant);
        user_name = findViewById(R.id.user_name_Restaurant);
        back_arrow_btn = findViewById(R.id.arrow_to_home);

        String user_Nameeee = getIntent().getStringExtra("user_Name");
        String pic_Profileeee = getIntent().getStringExtra("pic_Profile");

        //user_name.setText(user_Nameeee);
        //Picasso.get().load(pic_Profileeee).placeholder(R.drawable.user_image).into(photo);

       // detailsOfChatBinding.usernameChat.setText(user_Name);
       // Picasso.get().load(pic_Profile).placeholder(R.drawable.user_image).into(detailsOfChatBinding.profileImage);

        categories_List_Recycler_View();
        foods_List_Recycler_View();
        navigating_to_bottom1();

        back_arrow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(Restaurant_Main_DashBoard.this, Restaurant_Home_DashBoard.class);
                startActivity(o);
            }
        });


    }

    private void navigating_to_bottom1(){

        FloatingActionButton acting_floating_btn = findViewById(R.id.floating_Action_Buttonx);
        LinearLayout btn_Home = findViewById(R.id.btn_Homex);
        LinearLayout btn_Chat = findViewById(R.id.btn_chatx);
        LinearLayout btn_Settings = findViewById(R.id.btn_settingsx);
        LinearLayout btn_News = findViewById(R.id.btn_Newsx);

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Main_DashBoard.this, Restaurant_Main_DashBoard.class));
            }
        });

        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Main_DashBoard.this, Screen_Settings_user.class));
            }
        });

        btn_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Main_DashBoard.this, Screen_Chat.class));
            }
        });

        btn_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Main_DashBoard.this, Heading_News.class));
            }
        });

        acting_floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Main_DashBoard.this, Restaurant_Payment_Screen.class));
            }
        });


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
        restaurant_food_data.add(new Restaurant_Food_data("Extra Cheese, Beef or Ham, Tomato, Lettuce, Onions, Special Sauce","burger_pic",9.50,"Ham Burger"));
        restaurant_food_data.add(new Restaurant_Food_data("Olives, kalamata(Pitted), Oils(Vegitable,Olive), Fresh Oregano, Cherry Tomato, Basil", "veg_pizza_pic", 6.75, "Vegitable Pizza"));
        restaurant_food_data.add(new Restaurant_Food_data("Mozzerella Cheese, Slices Pepperoni, Ground Black Pepper, Fresh Oregano, Old Vintage Spices", "pizza_pic", 10.25, "Pepperoni Spicy Pizza"));
        restaurant_food_data.add(new Restaurant_Food_data("Ham or Beef, Lettuce, Onions, Spinach", "hot_dog_pic", 8.5, "Hot Dog"));
        recycle_adapter2 = new Adapter_Foods_List(restaurant_food_data);
        food_List_RecyclerView.setAdapter(recycle_adapter2);


    }


}