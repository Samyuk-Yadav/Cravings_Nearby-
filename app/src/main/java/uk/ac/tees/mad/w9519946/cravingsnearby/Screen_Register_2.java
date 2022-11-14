package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Screen_Register_2 extends AppCompatActivity {

    //Variables
    ImageView Back_Button;
    RadioGroup Group_Radio;
    RadioButton Radio_Button;
    DatePicker Age_Picker;
    Button Next;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register_2);
        getSupportActionBar().hide();

        //Hooks
        Back_Button = findViewById(R.id.back_btn_register);
        Group_Radio = findViewById(R.id.group_radio);
        Age_Picker = findViewById(R.id.picker_age);
        Next = findViewById(R.id.button_Next);
        Login = findViewById(R.id.button_Login);


    }

    public void callingNext3Screen(View v){

        if (!ageValidation() | !genderValidation()){
            return;
        }

        Radio_Button = findViewById(Group_Radio.getCheckedRadioButtonId());
        String mfoGender = Radio_Button.getText().toString();

        int select_day = Age_Picker.getDayOfMonth();
        int select_month = Age_Picker.getDayOfMonth();
        int select_year = Age_Picker.getYear();

        String dob = select_day + "/" + select_month + "/" + select_year;


        Intent i = new Intent(Screen_Register_2.this, Screen_Register_3.class);
        startActivity(i);
    }

    private boolean ageValidation(){
        int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
        int ageOfUser = Age_Picker.getYear();
        int validAge = yearCurrent - ageOfUser;

        if (validAge <= 15){
            Toast.makeText(this, "User! you are less than the required age limit", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean genderValidation(){
        if (Group_Radio.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Select your Gender too!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }



}