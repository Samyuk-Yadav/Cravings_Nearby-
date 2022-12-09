package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Screen_Final_BMI_Calculator extends AppCompatActivity {

    //Variables...
    android.widget.Button bmi_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_final_bmi_calculator);
        getSupportActionBar().hide();
        //Hooks...
        bmi_calculator = findViewById(R.id.bmi_calculator);


        bmi_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Screen_Final_BMI_Calculator.this, Screen_BMI_Calculator.class);
                startActivity(inte);
                finish();
            }
        });


    }
}