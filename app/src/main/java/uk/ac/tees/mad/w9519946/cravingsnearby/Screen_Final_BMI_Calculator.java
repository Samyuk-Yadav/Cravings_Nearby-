package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Screen_Final_BMI_Calculator extends AppCompatActivity {

    //Variables...
    TextView gender_BMI_Value;
    TextView cat_BMI_Value;
    float floating_BMI_Value;
    Intent xData;
    String calculated_BMI_Value;
    LottieAnimationView lottieAnimationView;
    TextView display_BMI_Value;
    android.widget.Button bmi_calculator;

    String user_Weight_Value;
    String user_Height_Value;
    RelativeLayout bg_relativeLayout;
    float floating_weight_value;
    float floating_height_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_final_bmi_calculator);
        getSupportActionBar().hide();
       // getSupportActionBar().setElevation(0);
       // getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
       // getSupportActionBar().setTitle("Your BMI is Here!!!");
       // ColorDrawable drawable_color = new ColorDrawable(Color.parseColor("#1E1D1ED"));
       // getSupportActionBar().setBackgroundDrawable(drawable_color);

        //Hooks...
        bg_relativeLayout = findViewById(R.id.layout_line);
        xData = getIntent();
        gender_BMI_Value = findViewById(R.id.gender_BMI_Value);
        cat_BMI_Value = findViewById(R.id.cat_BMI_Value);
        display_BMI_Value = findViewById(R.id.display_BMI_Value);
        lottieAnimationView = findViewById(R.id.animated_image);
        bmi_calculator = findViewById(R.id.bmi_calculator);


        bmi_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Screen_Final_BMI_Calculator.this, Screen_BMI_Calculator.class);
                startActivity(inte);
                finish();
            }
        });


        user_Height_Value = xData.getStringExtra("height_of_user");
        floating_height_value  = Float.parseFloat(user_Height_Value);
        floating_height_value = floating_height_value/100;
        user_Weight_Value = xData.getStringExtra("weight_of_user");
        floating_weight_value = Float.parseFloat(user_Weight_Value);
        floating_BMI_Value = floating_weight_value/(floating_height_value*floating_height_value);
        gender_BMI_Value.setText(xData.getStringExtra("gender_of_user"));


        calculated_BMI_Value = Float.toString(floating_BMI_Value);
        display_BMI_Value.setText(calculated_BMI_Value);

        if (floating_BMI_Value<16.1){
            cat_BMI_Value.setText("Under Weight");
            bg_relativeLayout.setBackgroundColor(Color.MAGENTA);
            lottieAnimationView.setAnimation(R.raw.cross);

        }else if(floating_BMI_Value>18.5 && floating_BMI_Value<25){
            cat_BMI_Value.setText("Normal");
            bg_relativeLayout.setBackgroundColor(Color.GREEN);
            lottieAnimationView.setAnimation(R.raw.ok);
        }
        else if(floating_BMI_Value>17.1 && floating_BMI_Value<18.5){
            cat_BMI_Value.setText("Mild Thinness");
            bg_relativeLayout.setBackgroundColor(Color.RED);
            lottieAnimationView.setAnimation(R.raw.warn);

        }else if (floating_BMI_Value>25 && floating_BMI_Value<29.5){
            cat_BMI_Value.setText("Over Weight (Obesity Type 1)");
            bg_relativeLayout.setBackgroundColor(Color.RED);
            lottieAnimationView.setAnimation(R.raw.warn);
        }
        else if(floating_BMI_Value>16.1 && floating_BMI_Value<16.9){
            cat_BMI_Value.setText("Moderate Weight");
            bg_relativeLayout.setBackgroundColor(Color.RED);
            lottieAnimationView.setAnimation(R.raw.warn);
        }
        else if(floating_BMI_Value>29.5 && floating_BMI_Value<35){
           cat_BMI_Value.setText("Obese (Obesity Type 2)");
           bg_relativeLayout.setBackgroundColor(Color.RED);
           lottieAnimationView.setAnimation(R.raw.cross);
        }


    }
}