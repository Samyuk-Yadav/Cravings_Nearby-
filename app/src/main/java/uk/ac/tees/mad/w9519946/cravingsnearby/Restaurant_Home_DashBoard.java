package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantHomeDashBoardBinding;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenChatBinding;

public class Restaurant_Home_DashBoard extends AppCompatActivity {

    FirebaseAuth firebaseAuth12;
    FirebaseDatabase firebaseDatabase12;
    ActivityRestaurantHomeDashBoardBinding homeDashBoardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeDashBoardBinding = ActivityRestaurantHomeDashBoardBinding.inflate(getLayoutInflater());
        setContentView(homeDashBoardBinding.getRoot());

        getSupportActionBar().setTitle("Welcome OnBoard! Peeps");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Background)));

        firebaseAuth12 = FirebaseAuth.getInstance();
        firebaseDatabase12 = FirebaseDatabase.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu_restaurant) {

        getMenuInflater().inflate(R.menu.restaurant_main_menu,menu_restaurant);

        return super.onCreateOptionsMenu(menu_restaurant);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.user_setting:
                Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
                Intent intent_User_Settings = new Intent(Restaurant_Home_DashBoard.this, Screen_Settings_user.class);
                startActivity(intent_User_Settings);
                break;

            case R.id.watch_News:
                Toast.makeText(this, "News Clicked!", Toast.LENGTH_SHORT).show();
                Intent intent_watch_news = new Intent(Restaurant_Home_DashBoard.this, Heading_News.class);
                startActivity(intent_watch_news);
                break;

            case R.id.calculate_BMI:
                Toast.makeText(this, "Check Your BMI Here!", Toast.LENGTH_SHORT).show();
                Intent intent_calculate_BMI = new Intent(Restaurant_Home_DashBoard.this, Screen_BMI_Calculator.class);
                startActivity(intent_calculate_BMI);
                break;

            case R.id.logout:
                firebaseAuth12.signOut();
                Intent intent_logout = new Intent(Restaurant_Home_DashBoard.this, Screen_Login.class);
                startActivity(intent_logout);
                break;

        }
        return true;
    }



}