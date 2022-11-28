package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantLoginBinding;

public class Restaurant_Login extends AppCompatActivity {
    //Variables
    ActivityRestaurantLoginBinding loginBinding;
    FirebaseAuth firebase1Auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = ActivityRestaurantLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        getSupportActionBar().hide();

        //Progress Dialog Implementation
        dialog = new ProgressDialog(Restaurant_Login.this);
        dialog.setTitle("Status Login");
        dialog.setMessage("Logging into your Account!");

        //Hooks
        firebase1Auth = FirebaseAuth.getInstance();

        //Login to the created account
        loginBinding.buttonNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                firebase1Auth.signInWithEmailAndPassword(loginBinding.emailRestaurant.getEditText().getText().toString(),
                        loginBinding.passwordRestaurant.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.dismiss();

                        if (task.isSuccessful()){

                            Toast.makeText(Restaurant_Login.this, "Successfully Signed In!", Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(Restaurant_Login.this, MainActivity.class);
                            startActivity(inte);
                        }
                        else{

                            Toast.makeText(Restaurant_Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Switch to Register
        loginBinding.doNotHaveAnAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Restaurant_Login.this, Restaurant_Register.class);
                startActivity(inte);
            }
        });

        //Keep current user Logged In
        if (firebase1Auth.getCurrentUser()!= null){
            Intent inte = new Intent(Restaurant_Login.this, Screen_Chat.class);
            startActivity(inte);
        }





    }
}