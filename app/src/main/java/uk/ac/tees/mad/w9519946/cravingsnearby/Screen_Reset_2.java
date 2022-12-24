package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenReset2Binding;

public class Screen_Reset_2 extends AppCompatActivity {

    ActivityScreenReset2Binding activityScreenReset2Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityScreenReset2Binding = ActivityScreenReset2Binding.inflate(getLayoutInflater());
        setContentView(activityScreenReset2Binding.getRoot());
        getSupportActionBar().hide();

        activityScreenReset2Binding.reset2BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Screen_Reset_2.this, Screen_Reset_1.class);
                startActivity(x);
            }
        });

        activityScreenReset2Binding.buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Screen_Reset_2.this, "First Approve the link sent to your Phone Number!", Toast.LENGTH_LONG).show();
                Intent y = new Intent(Screen_Reset_2.this, Screen_Reset_3.class);
                startActivity(y);
            }
        });

        activityScreenReset2Binding.buttonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Screen_Reset_2.this, "First Approve the link sent to your Email ID!", Toast.LENGTH_LONG).show();
                Intent y = new Intent(Screen_Reset_2.this, Screen_Reset_3.class);
                startActivity(y);
            }
        });





    }
}