package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import java.util.concurrent.TimeUnit;

public class Screen_OTP extends AppCompatActivity {
    //Variables
    PinView OTP_Pin;
    String verificationId;
    ProgressBar ProgressBar;
    ImageView Close;
    Button Next;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_otp);
        getSupportActionBar().hide();

        //Hooks
        Close = findViewById(R.id.close);
        Next = findViewById(R.id.button_NextOTP);
        ProgressBar = findViewById(R.id.show_Progress);
        OTP_Pin = findViewById(R.id.view_Pin);

        String number_phone = getIntent().getStringExtra("register_phoneNo");
        ProgressBar.setVisibility(View.VISIBLE);
        otp_sent_to_user(number_phone);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(number_phone)) {
                    Toast.makeText(Screen_OTP.this, "Enter Valid OTP...", Toast.LENGTH_SHORT).show();
                } else {
                    codeVerify(OTP_Pin.getText().toString());
                }
            }
        });


    }

    private void otp_sent_to_user(String register_phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(register_phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCall)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCall = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            final String otp = credential.getSmsCode();
            if (otp != null) {
                codeVerify(otp);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            // Show a message and update the UI
            Toast.makeText(Screen_OTP.this, "Verification Successful", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String ID, @NonNull PhoneAuthProvider.ForceResendingToken token1) {
            // Save verification ID and resending token so we can use them later
            super.onCodeSent(ID, token1);
            verificationId = ID;
            Toast.makeText(Screen_OTP.this, "Please Verify the Code Sent", Toast.LENGTH_SHORT).show();
            Next.setEnabled(true);
            ProgressBar.setVisibility(View.INVISIBLE);
        }
    };


    private void codeVerify(String otp) {
        PhoneAuthCredential credential_otp = PhoneAuthProvider.getCredential(verificationId, otp);
        credentialSignIn(credential_otp);
    }

    private void credentialSignIn(PhoneAuthCredential credential) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Screen_OTP.this, "Successfully Verified!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Screen_OTP.this, Screen_Login.class);

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Screen_OTP.this, "Failed to Verify! Please Try Again...", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser_current = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser_current!= null){
            Intent i = new Intent(Screen_OTP.this, Screen_Login.class);
            startActivity(i);
            finish();
        }
    }
}