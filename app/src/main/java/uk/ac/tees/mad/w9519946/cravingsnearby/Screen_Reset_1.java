package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenReset1Binding;

public class Screen_Reset_1 extends AppCompatActivity {

    ActivityScreenReset1Binding activityScreenReset1Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityScreenReset1Binding = ActivityScreenReset1Binding.inflate(getLayoutInflater());
        setContentView(activityScreenReset1Binding.getRoot());
        getSupportActionBar().hide();

        activityScreenReset1Binding.backForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Screen_Reset_1.super.onBackPressed();
            }
        });


        activityScreenReset1Binding.buttonRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!activityScreenReset1Binding.emailAddress.getEditText().getText().toString().isEmpty()){
                    Intent x = new Intent(Screen_Reset_1.this, Screen_Reset_2.class);
                    startActivity(x);
                    Toast.makeText(Screen_Reset_1.this, "Select How to Receive a Reset Code", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Screen_Reset_1.this, "Please Give Correct Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}