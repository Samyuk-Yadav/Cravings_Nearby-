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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantRegisterBinding;

public class Restaurant_Register extends AppCompatActivity {

    private FirebaseAuth register_firebaseAuth;
    FirebaseDatabase register_database;
    ProgressDialog dialog;
    ActivityRestaurantRegisterBinding registerBinding;
    TextInputLayout Name_1;
    TextInputLayout Email_1;
    TextInputLayout Password_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRestaurantRegisterBinding.inflate(getLayoutInflater());

        //binding the layout done.
        setContentView(registerBinding.getRoot());
        getSupportActionBar().hide();

        //Hooks
        Email_1 = findViewById(R.id.email_1);
        Name_1 = findViewById(R.id.name_1);
        Password_1 = findViewById(R.id.password_1);

        //Progress Dialog Implementation
        dialog = new ProgressDialog(Restaurant_Register.this);
        dialog.setTitle("Account Creation Status");
        dialog.setMessage("Your Account is being Created Sucessfully!");

        //Take Auth, Database Instance
        register_firebaseAuth = FirebaseAuth.getInstance();
        register_database = FirebaseDatabase.getInstance();

        //Register Button
        registerBinding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                register_firebaseAuth.createUserWithEmailAndPassword(registerBinding.emailRestaurant.getEditText().getText().toString(),
                        registerBinding.passwordRestaurant.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task_register) {

                        dialog.dismiss();

                        if (task_register.isSuccessful()){

                            //save values in database...
                            Users_data usersData = new Users_data(registerBinding.nameRestaurant.getEditText().getText().toString(),
                                    registerBinding.emailRestaurant.getEditText().getText().toString(),
                                    registerBinding.passwordRestaurant.getEditText().getText().toString());

                            //Get Id from Firebase...
                            String id_Firebase = task_register.getResult().getUser().getUid();

                            //Set data of Restaurant to Firebase Database...
                            register_database.getReference().child("Restaurants List").child(id_Firebase).setValue(usersData);

                            Toast.makeText(Restaurant_Register.this , "Creation of Restaurant is Sucessful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Restaurant_Register.this, task_register.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });

        //switch to Login
        registerBinding.alreadyHaveAnAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Restaurant_Register.this, Restaurant_Login.class);
                startActivity(inte);
            }
        });







    }
}