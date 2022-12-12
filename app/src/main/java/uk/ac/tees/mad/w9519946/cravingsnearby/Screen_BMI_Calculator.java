package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Screen_BMI_Calculator extends AppCompatActivity {

    //Variables...
    String user_type_ = "0";
    String current_user_weight = "70";
    String current_user_age = "24";
    String prog_in_mi = "142";

    int current_weight_default = 71;
    int live_prog;
    int current_age_default = 24;

    RelativeLayout boy_gender;
    RelativeLayout girl_gender;
    TextView height_present_now;
    TextView age_Number_txt;
    ImageView weight_plus1;
    SeekBar length_of_seekbar;
    TextView weight_Number_txt;
    ImageView age1_minus;
    ImageView weight_minus1;
    ImageView age1_plus;
    android.widget.Button bmi_calculator1_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_bmi_calculator);
        getSupportActionBar().hide();

        //Hooks...TypeCasting...
        age_Number_txt = findViewById(R.id.age_Number_txt);
        length_of_seekbar = findViewById(R.id.length_of_seekbar);
        boy_gender = findViewById(R.id.boy_gender);
        age1_minus = findViewById(R.id.age1_minus);
        girl_gender = findViewById(R.id.girl_gender);
        weight_minus1 = findViewById(R.id.weight_minus);
        age1_plus = findViewById(R.id.age1_plus);
        length_of_seekbar.setProgress(169);
        weight_plus1 = findViewById(R.id.weight_plus);
        length_of_seekbar.setMax(280);
        height_present_now = findViewById(R.id.height_present_now);
        weight_Number_txt  = findViewById(R.id.weight_Number_txt);
        bmi_calculator1_btn = findViewById(R.id.bmi_calculator1);

        weight_plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_weight_default += 1;
                current_user_weight = String.valueOf(current_weight_default);
                weight_Number_txt.setText(current_user_weight);

            }
        });

        age1_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_age_default += 1;
                current_user_age = String.valueOf(current_age_default);
                age_Number_txt.setText(current_user_age);

            }
        });

        girl_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girl_gender.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focusing_gender_background));
                boy_gender.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.not_focusing_gender_background));
                user_type_ = "Female";
            }
        });


        bmi_calculator1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_type_.equals("0")){
                    Toast.makeText(Screen_BMI_Calculator.this, "Select Gender First!!!", Toast.LENGTH_SHORT).show();
                }else if (current_weight_default<0 || current_weight_default==0){
                    Toast.makeText(Screen_BMI_Calculator.this, "Incorrect Weight!!!", Toast.LENGTH_SHORT).show();
                }
                else if(current_age_default<0 || current_age_default==0){
                    Toast.makeText(Screen_BMI_Calculator.this, "Incorrect Age!!!", Toast.LENGTH_SHORT).show();
                }
                else if(prog_in_mi.equals("0")){
                    Toast.makeText(Screen_BMI_Calculator.this, "Select Height First!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent x = new Intent(Screen_BMI_Calculator.this, Screen_Final_BMI_Calculator.class);
                    x.putExtra("height_of_user", prog_in_mi);
                    x.putExtra("age_of_user", current_user_age);
                    x.putExtra("weight_of_user", current_user_weight);
                    x.putExtra("gender_of_user", user_type_);
                    startActivity(x);
                }
            }
        });

        boy_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boy_gender.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focusing_gender_background));
                girl_gender.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.not_focusing_gender_background));
                user_type_ = "Male";
            }
        });

        length_of_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sbar, int i, boolean b) {
                live_prog = i;
                prog_in_mi = String.valueOf(live_prog);
                height_present_now.setText(prog_in_mi);
            }

            @Override
            public void onStartTrackingTouch(SeekBar sbar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sbar) {

            }
        });

        weight_minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_weight_default -= 1;
                current_user_weight = String.valueOf(current_weight_default);
                weight_Number_txt.setText(current_user_weight);

            }
        });

        age1_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_age_default -= 1;
                current_user_age = String.valueOf(current_age_default);
                age_Number_txt.setText(current_user_age);

            }
        });


    }
}