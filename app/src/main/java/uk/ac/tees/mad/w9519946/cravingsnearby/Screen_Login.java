package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Screen_Login extends AppCompatActivity {

    //Variables
    Button Back_btn;
    Button Login;
    Button Google;
    Button Facebook;
    TextView Forgot_password;
    TextView No_account;
    CheckBox Remember_me;
    TextInputLayout Email;
    TextInputLayout Password;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_login);
        getSupportActionBar().hide();

        //Hooks
        Back_btn = findViewById(R.id.back_btnl);
        Login = findViewById(R.id.button_Login);
        Google = findViewById(R.id.button_Google_SignIn_l);
        Facebook = findViewById(R.id.button_Facebook_SignIn_l);
        Forgot_password = findViewById(R.id.forget_passwordl);
        No_account = findViewById(R.id.no_account_login);
        Remember_me = findViewById(R.id.remember_me);
        Email = findViewById(R.id.email_login);
        Password = findViewById(R.id.password_login);

        //OnClick Listener

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Screen_Login.this, Choice_Activity.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Screen_Login.this, MainActivity.class);
                startActivity(i);
            }
        });







    }
}