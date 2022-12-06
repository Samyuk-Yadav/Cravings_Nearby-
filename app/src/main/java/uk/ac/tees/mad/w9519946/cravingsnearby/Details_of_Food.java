package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper.Cart_Shopping;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;

public class Details_of_Food extends AppCompatActivity {
    //Variables
    private TextView text_title;
    private TextView btn_Add_Item_To_Cart;
    private TextView text_Order_Number;
    private ImageView btn_Minus;
    private Cart_Shopping cartShopping;
    private Restaurant_Food_data restaurantFoodData;
    private ImageView btn_Plus;
    private ImageView btn_Food_Pic;
    int count_order_number = 1;
    private TextView text_Price;
    private TextView text_Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_food);
        getSupportActionBar().hide();

        cartShopping = new Cart_Shopping(this);


        details_Of_Food();
        import_Restaurant_Food_Data();



    }

    private void details_Of_Food() {
        //hooks
        text_title = findViewById(R.id.text_title);
        btn_Plus = findViewById(R.id.imageButton_plus);
        text_Order_Number = findViewById(R.id.text_Order_Number);
        text_Description = findViewById(R.id.textview_description);
        btn_Minus = findViewById(R.id.imageButton_minus);
        text_Price = findViewById(R.id.textView_price);
        btn_Food_Pic = findViewById(R.id.imageView_pizzaa);
        btn_Add_Item_To_Cart = findViewById(R.id.add_Item_To_Cart_Btn);
    }
    private void import_Restaurant_Food_Data() {
        restaurantFoodData = (Restaurant_Food_data) getIntent().getSerializableExtra("restaurantFoodData");
        int id_Of_Rescource = this.getResources().getIdentifier(restaurantFoodData.getFood_pic(), "drawable", this.getPackageName());
        text_Price.setText("£"+ restaurantFoodData.getFood_fees());
        text_Order_Number.setText(String.valueOf(count_order_number));
        text_Description.setText(restaurantFoodData.getFood_description());
        text_title.setText(restaurantFoodData.getFood_title());
        Glide.with(this).load(id_Of_Rescource).into(btn_Food_Pic);

        btn_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count_order_number>1){
                    count_order_number = count_order_number-1;
                }text_Order_Number.setText(String.valueOf(count_order_number));
            }
        });

        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_order_number = count_order_number+1;
                text_Order_Number.setText(String.valueOf(count_order_number));
            }
        });

        btn_Add_Item_To_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantFoodData.setCurrency_in_Cart(count_order_number);
                cartShopping.food_applying(restaurantFoodData);
            }
        });




    }
}