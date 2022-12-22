package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Customers_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenOtpBinding;

public class Screen_OTP extends AppCompatActivity {
    //Variables
    PinView OTP_Pin;
    ProgressBar ProgressBar;
    ProgressDialog dialog123;
    ImageView Close;
    Button Next;
    private FirebaseAuth firebaseAuth123;
    FirebaseDatabase firebaseDatabase123;
    ActivityScreenOtpBinding otpBinding;

    //Global variables Declaration
    String register_username;
    String register_password;
    String register_email;
    String register_date;
    String register_gender;
    String number_phone;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otpBinding = ActivityScreenOtpBinding.inflate(getLayoutInflater());
        setContentView(otpBinding.getRoot());
        getSupportActionBar().hide();

        //Hooks
        Close = findViewById(R.id.close);
        Next = findViewById(R.id.button_NextOTP1);
        ProgressBar = findViewById(R.id.show_Progress);
        OTP_Pin = findViewById(R.id.view_Pin);
        firebaseAuth123 = FirebaseAuth.getInstance();
        firebaseDatabase123 = FirebaseDatabase.getInstance();

        register_username = getIntent().getStringExtra("register_username");
        register_password = getIntent().getStringExtra("register_password");
        register_email = getIntent().getStringExtra("register_email");
        register_date = getIntent().getStringExtra("register_date");
        register_gender = getIntent().getStringExtra("register_gender");

        number_phone = getIntent().getStringExtra("register_phoneNo");
        ProgressBar.setVisibility(View.VISIBLE);
        dialog123 = new ProgressDialog(Screen_OTP.this);
        dialog123.setTitle("Status: Creating User UI");
        dialog123.setMessage("Your account is being created!");

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog123.show();
                firebaseAuth123.createUserWithEmailAndPassword(register_email.toString(), register_password.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> focus) {
                        dialog123.dismiss();
                        if (focus.isSuccessful()){
                            Customers_data customers_data = new Customers_data(register_username, register_password, register_email, register_date, register_gender, number_phone);
                            String user_idd = focus.getResult().getUser().getUid();
                            firebaseDatabase123.getReference().child("Customers Data").child(user_idd).setValue(customers_data);
                            Toast.makeText(Screen_OTP.this, "Successfully account created", Toast.LENGTH_SHORT).show();

                            Intent inte1111 = new Intent(Screen_OTP.this, Restaurant_Home_DashBoard.class);
                            startActivity(inte1111);
                        }
                        else {
                            Toast.makeText(Screen_OTP.this, focus.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a message and update the UI
                Toast.makeText(Screen_OTP.this, "Verify all your Details or If already a customer than Login Directly", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Screen_OTP.this, Screen_Register_1.class));
            }
        });


    }
}