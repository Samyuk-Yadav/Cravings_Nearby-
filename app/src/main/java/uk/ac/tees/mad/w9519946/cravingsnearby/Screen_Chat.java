package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityMainBinding;

public class Screen_Chat extends AppCompatActivity {

    //variables
    ActivityMainBinding mainBinding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //Hooks
        firebaseAuth = FirebaseAuth.getInstance();


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
                Intent inte = new Intent(Screen_Chat.this, Screen_Login.class);
                startActivity(inte);
                break;

        }
        return true;
    }
}
