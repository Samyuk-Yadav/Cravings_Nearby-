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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Customers_data;

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



        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Register_3.this, Screen_Register_2.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Register_3.this, Screen_Login.class));
            }
        });



    }

    public void verifyScreen_OTP(View v){

        if (!number_Phone_Validation()){
            return;
        }
        Intent intent = getIntent();
        String username_r = intent.getStringExtra("register_username");
        String password_r = intent.getStringExtra("register_password");
        String email_r = intent.getStringExtra("register_email");
        String date_r = intent.getStringExtra("register_date");
        String gender_r = intent.getStringExtra("register_gender");

        String userGivenNumber = Number_Phone.getEditText().getText().toString().trim();
//        Log.v("ScreenReg3", Picker_Code.getFullNumber());
        String s = Picker_Code.getSelectedCountryCodeWithPlus();
//        Log.v("ScreenReg3", s);
//        Log.v("ScreenReg3", Picker_Code.getDefaultCountryCodeWithPlus());
        String phone_r = s + userGivenNumber;
//        String phone_r = "+" + Picker_Code.getDefaultCountryCode() + userGivenNumber;



        Intent i = new Intent(Screen_Register_3.this, Screen_OTP.class);
        //below fields are send to next activity
        i.putExtra("register_username", username_r);
        i.putExtra("register_password", password_r);
        i.putExtra("register_email", email_r);
        i.putExtra("register_date", date_r);
        i.putExtra("register_gender", gender_r);
        i.putExtra("register_phoneNo", phone_r);
        startActivity(i);

    }

    private boolean number_Phone_Validation() {
        String numberCheck = "^[0-9]{10}$";
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