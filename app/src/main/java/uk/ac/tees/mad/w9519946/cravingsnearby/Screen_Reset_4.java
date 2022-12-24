package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenReset4Binding;

public class Screen_Reset_4 extends AppCompatActivity {

    ActivityScreenReset4Binding activityScreenReset4Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityScreenReset4Binding = ActivityScreenReset4Binding.inflate(getLayoutInflater());
        setContentView(activityScreenReset4Binding.getRoot());
        getSupportActionBar().hide();

        activityScreenReset4Binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent W = new Intent(Screen_Reset_4.this, Screen_Login.class);
                startActivity(W);
            }
        });
    }
}