package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Restaurant_Final_Screen extends AppCompatActivity {

    //Variables
    Button button_Explore1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_final_screen);
        getSupportActionBar().hide();
        button_Explore1 = findViewById(R.id.button_Explore111);


        button_Explore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Restaurant_Final_Screen.this, "Now Relax and Wait for Your Order, Till then Watch What's trending Nowadays In our Cravings News!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Restaurant_Final_Screen.this, Restaurant_Home_DashBoard.class));
            }
        });



    }
}