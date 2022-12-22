package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Screen_Login extends AppCompatActivity {

    //Variables
    ImageView Back_btn;
    Button Login;
    Button Google;
    Button Facebook;
    FirebaseAuth firebaseAuth1231;
    ProgressDialog djdialog;
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

        djdialog = new ProgressDialog(Screen_Login.this);
        djdialog.setTitle("Welcome Back");
        djdialog.setMessage("Account Login Here!");

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
        firebaseAuth1231 = FirebaseAuth.getInstance();


        //OnClick Listener
        No_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Login.this, Screen_Register_1.class));
            }
        });

        Forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Login.this, Screen_Reset_1.class));
            }
        });

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Screen_Login.this, Choice_Activity.class);
                startActivity(i);
            }
        });

        //Change Destination
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                djdialog.show();
                firebaseAuth1231.signInWithEmailAndPassword(Email.getEditText().getText().toString(), Password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> focus) {
                        djdialog.dismiss();
                        if (focus.isSuccessful()){
                            Intent i = new Intent(Screen_Login.this, Restaurant_Home_DashBoard.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(Screen_Login.this, focus.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        if (firebaseAuth1231.getCurrentUser()!=null)
        {
            Intent inteeee = new Intent(Screen_Login.this, Restaurant_Home_DashBoard.class);
            startActivity(inteeee);
        }
    }
}