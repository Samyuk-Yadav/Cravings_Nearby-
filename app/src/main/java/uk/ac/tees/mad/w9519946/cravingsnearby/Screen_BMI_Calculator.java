package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Screen_BMI_Calculator extends AppCompatActivity {

    //Variables...
    android.widget.Button bmi_calculator1_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_bmi_calculator);
        getSupportActionBar().hide();

        //Hooks...
        bmi_calculator1_btn = findViewById(R.id.bmi_calculator1);

        bmi_calculator1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Screen_BMI_Calculator.this, Screen_Final_BMI_Calculator.class);
                startActivity(x);
            }
        });
    }
}