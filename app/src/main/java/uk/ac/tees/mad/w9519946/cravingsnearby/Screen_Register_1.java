package uk.ac.tees.mad.w9519946.cravingsnearby;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.jar.Attributes;

public class Screen_Register_1 extends AppCompatActivity {

    //Variables
    TextInputLayout Name_1, Email_1, Password_1;
    Button Google_button, Next_button;
    ImageView Back_button;
    TextView Already_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register_1);
        getSupportActionBar().hide();

        //Hooks
        Back_button = findViewById(R.id.back_btn1);
        Email_1 = findViewById(R.id.email_1);
        Name_1 = findViewById(R.id.name_1);
        Password_1 = findViewById(R.id.password_1);
        Already_have_account = findViewById(R.id.already_Have_an_Account_Text);
        Google_button = findViewById(R.id.button_1Google_Register);
        Next_button = findViewById(R.id.button_Next1);


    }

    public void callingNext2Screen(View v) {

        if (!userNameValidation() | !emailValidation() | !passwordValidation()){
            return;
        }

        //Calling Next Screen
        Intent i = new Intent(Screen_Register_1.this, Screen_Register_2.class);
        i.putExtra("register_username", Name_1.getEditText().getText().toString().trim());
        i.putExtra("register_email", Email_1.getEditText().getText().toString().trim());
        i.putExtra("register_password", Password_1.getEditText().getText().toString().trim());
        startActivity(i);
    }

    private boolean userNameValidation() {
        String spaceCheck = "\\A\\w{2,20}\\z";
        String details = Name_1.getEditText().getText().toString().trim();

        if (details.isEmpty()) {
            Name_1.setError("Empty Field! Enter Name.");
            return false;
        } else if (spaceCheck.length() > 20) {
            Name_1.setError("Too big Name!");
            return false;
        } else if (!details.matches(spaceCheck)) {
            Name_1.setError("No (White) spaces allowed");
            return false;
        } else {
            Name_1.setError(null);
            Name_1.setErrorEnabled(false);
            return true;
        }
    }

    private boolean emailValidation() {
        String emailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String details = Email_1.getEditText().getText().toString().trim();

        if (details.isEmpty()) {
            Email_1.setError("Empty Field! Enter Email.");
            return false;
        } else if (!details.matches(emailCheck)) {
            Email_1.setError("Invalid Email ID Format!");
            return false;
        } else {
            Email_1.setError(null);
            Email_1.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordValidation() {
        String passwordCheck = "^(?=[A-Z0-9]*[a-z])(?=[a-zA-Z]*[0-9])(?=[a-z0-9]*[A-Z])[a-zA-Z0-9]{8,}$";
        String details = Password_1.getEditText().getText().toString().trim();

        if (details.isEmpty()) {
            Password_1.setError("Empty Field! Enter Password.");
            return false;
        }else if (details.length() < 6){
            Password_1.setError("Password Too small, Atleast 6 Chars");

        } else if (!details.matches(passwordCheck)) {
            Password_1.setError("Atleast one Captial and small letter, number and no special characters");
            return false;
        } else {
            Password_1.setError(null);
            Password_1.setErrorEnabled(false);
            return true;
        }
        return false;
    }


}