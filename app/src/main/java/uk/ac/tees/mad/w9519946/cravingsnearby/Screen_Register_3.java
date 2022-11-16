package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Screen_Register_3 extends AppCompatActivity {

    //Variables
    ImageView Back_Button;
    CountryCodePicker Picker_Code;
    TextInputLayout Number_Phone;
    Button Next;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register_3);
        getSupportActionBar().hide();

        //Hooks
        Back_Button = findViewById(R.id.back_btn3);
        Picker_Code = findViewById(R.id.desi_code);
        Number_Phone = findViewById(R.id.number_register);
        Next = findViewById(R.id.button_Next_Register);
        Login = findViewById(R.id.button_Register3);

    }

    public void verifyScreen_OTP(View v){

        if (!number_Phone_Validation()){
            return;
        }

        String username_r = getIntent().getStringExtra("register_username");
        String password_r = getIntent().getStringExtra("register_password");
        String email_r = getIntent().getStringExtra("register_email");
        String date_r = getIntent().getStringExtra("register_date");
        String gender_r = getIntent().getStringExtra("register_gender");

        String userGivenNumber = Number_Phone.getEditText().getText().toString().trim();
        String phone_r = "+" + Picker_Code.getFullNumber() + userGivenNumber;


        Intent i = new Intent(Screen_Register_3.this, Screen_OTP.class);
        //below fields are send to next activity
        i.putExtra("register_username", username_r);
        i.putExtra("register_password", password_r);
        i.putExtra("register_email", email_r);
        i.putExtra("register_username", date_r);
        i.putExtra("register_gender", gender_r);
        i.putExtra("register_phoneNo", phone_r);





    }

    private boolean number_Phone_Validation() {
        String numberCheck = "^[0-9]{9,11}$";
        String details = Number_Phone.getEditText().getText().toString().trim();

        if (details.isEmpty()) {
            Number_Phone.setError("Empty Field! Enter Phone Number.");
            return false;
        } else if (!details.matches(numberCheck)) {
            Number_Phone.setError("Invalid Number Format! Please enter only 10 digits of you Mobile Number");
            return false;
        } else {
            Number_Phone.setError(null);
            Number_Phone.setErrorEnabled(false);
            return true;
        }
    }


}