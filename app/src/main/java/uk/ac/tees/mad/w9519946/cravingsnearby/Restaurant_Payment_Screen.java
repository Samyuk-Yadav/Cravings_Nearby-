package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Final_Cart;
import uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper.Cart_Shopping;
import uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities.Cart_Number_Of_Items;

public class Restaurant_Payment_Screen extends AppCompatActivity {

    //variables
    private Cart_Shopping cartShopping;
    TextView items_In_Cart;
    private ScrollView view_scrollingView;
    TextView delivery_Fees;
    TextView cart_Empty;
    private double fees_taxation;
    Button button_checkout;
    TextView taxation;
    TextView total_Fees;
    private RecyclerView listing_recyclerView;
    private RecyclerView.Adapter recycler_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_payment_screen);
        getSupportActionBar().hide();

        //hooks
        button_checkout = findViewById(R.id.button_checkout);
        cartShopping = new Cart_Shopping(this);
        payment_Screen();
        list_of_Orders();
        FinalCartValue();
        navigating_to_bottom();

        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Restaurant_Payment_Screen.this, "Order Placed Successfully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Restaurant_Payment_Screen.this, Restaurant_Final_Screen.class));
            }
        });


    }

    private void navigating_to_bottom(){

        LinearLayout btn_Home = findViewById(R.id.btn_Home69);
        FloatingActionButton acting_floating_btn = findViewById(R.id.acting_floating_Button);

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Payment_Screen.this, Restaurant_Main_DashBoard.class));
            }
        });

        acting_floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant_Payment_Screen.this, Restaurant_Payment_Screen.class));
            }
        });


    }

    private void FinalCartValue(){
        double taxation_percentage = 0.03;
        double charge_delivery = 10.5;

        fees_taxation =  Math.round((cartShopping.getcompleteCost()*taxation_percentage) * 100)/100;
        double total_items_buying = Math.round(cartShopping.getcompleteCost()*100)/100;
        double final_total_amount = Math.round((cartShopping.getcompleteCost() + fees_taxation + charge_delivery)*100)/100;

        total_Fees.setText("£" + final_total_amount);//total final
        delivery_Fees.setText("£" + charge_delivery);//delivery
        taxation.setText("£"+ fees_taxation);//
        items_In_Cart.setText("£"+total_items_buying);//items in cart

    }

    private void list_of_Orders() {
        LinearLayoutManager manager_Of_Layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        listing_recyclerView.setLayoutManager(manager_Of_Layout);
        recycler_Adapter = new Adapter_Final_Cart(cartShopping.cart_Shopping_List(), this, new Cart_Number_Of_Items() {
            @Override
            public void swaping() {
                FinalCartValue();

            }
        });
        listing_recyclerView.setAdapter(recycler_Adapter);
        if (cartShopping.cart_Shopping_List().isEmpty()){
            cart_Empty.setVisibility(View.VISIBLE);
            view_scrollingView.setVisibility(View.GONE);
        }else {
            cart_Empty.setVisibility(View.GONE);
            view_scrollingView.setVisibility(View.VISIBLE);
        }
    }

    private void payment_Screen() {
        items_In_Cart = findViewById(R.id.items_In_Cart);
        delivery_Fees = findViewById(R.id.delivery_Fees);
        cart_Empty = findViewById(R.id.cart_Empty);
        listing_recyclerView = findViewById(R.id.view_recycle);
        total_Fees = findViewById(R.id.total_Fees);
        taxation = findViewById(R.id.taxation);
        view_scrollingView =findViewById(R.id.scrollView269);


    }
}