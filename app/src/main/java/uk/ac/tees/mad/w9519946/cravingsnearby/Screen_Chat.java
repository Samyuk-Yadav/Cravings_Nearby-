package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Fragments;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityMainBinding;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenChatBinding;

public class Screen_Chat extends AppCompatActivity {

    //variables
    ActivityScreenChatBinding screenChatBinding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenChatBinding = ActivityScreenChatBinding.inflate(getLayoutInflater());
        setContentView(screenChatBinding.getRoot());
      //  getSupportActionBar().setTitle("                 Customer Chats");
        getSupportActionBar().setTitle(getString(R.string.customer_chat));

        //Hooks
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Tab Layout setup
        screenChatBinding.pagerIDView.setAdapter(new Adapter_Fragments(getSupportFragmentManager()));
        screenChatBinding.topLayoutOfTab.setupWithViewPager(screenChatBinding.pagerIDView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu user_menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.customer_chat_menu, user_menu);
        return super.onCreateOptionsMenu(user_menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.user_setting:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                firebaseAuth.signOut();
                Intent inte = new Intent(Screen_Chat.this, Restaurant_Login.class);
                startActivity(inte);
                break;

        }
        return true;
    }
}
