package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenReset3Binding;

public class Screen_Reset_3 extends AppCompatActivity {

    ActivityScreenReset3Binding activityScreenReset3Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityScreenReset3Binding = ActivityScreenReset3Binding.inflate(getLayoutInflater());
        setContentView(activityScreenReset3Binding.getRoot());
        getSupportActionBar().hide();


        activityScreenReset3Binding.back3Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Screen_Reset_3.this, Screen_Reset_2.class);
                startActivity(i);
            }
        });

        activityScreenReset3Binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityScreenReset3Binding.newPassword.getEditText().getText().toString().equals(activityScreenReset3Binding.newPasswordRepeat.getEditText().getText().toString())) {
                    Toast.makeText(Screen_Reset_3.this, "Sucessfully Changed the Password, Now Login with New Password!", Toast.LENGTH_SHORT).show();
                    Intent z = new Intent(Screen_Reset_3.this, Screen_Reset_4.class);
                    startActivity(z);
                } else {
                    Toast.makeText(Screen_Reset_3.this, "Both Passwords Should Be Same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}