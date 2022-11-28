package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantRegisterBinding;

public class Restaurant_Register extends AppCompatActivity {

    //variables
    FirebaseAuth register_firebaseAuth;
    FirebaseDatabase register_database;
    ProgressDialog dialog;
    GoogleSignInClient userRegister;
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

                            //Toast
                            Toast.makeText(Restaurant_Register.this , "Creation of Restaurant is Sucessful", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(Restaurant_Register.this, task_register.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                Toast.makeText(Restaurant_Register.this, "Redirecting you to Login Page", Toast.LENGTH_SHORT).show();
                //Move to Login Screen
                Intent inte = new Intent(Restaurant_Register.this, Restaurant_Login.class);
                startActivity(inte);
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

        //Register/SigIn using Google
        registerBinding.buttonGoogleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        //Keep current user Logged In
        FirebaseAuth firebase1Auth;
        firebase1Auth = FirebaseAuth.getInstance();
        if (firebase1Auth.getCurrentUser()!= null){
            Intent inte = new Intent(Restaurant_Register.this, Screen_Chat.class);
            startActivity(inte);
        }


    }

    int RC_Log_In = 84;
    private void logIn() {
        Intent signInIntent = userRegister.getSignInIntent();
        startActivityForResult(signInIntent, RC_Log_In);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_Log_In) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                //Method for creating Firebase...
                google_Authentication_using_Firebase(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void google_Authentication_using_Firebase(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        register_firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser firebaseUser = register_firebaseAuth.getCurrentUser();

                            //Firebase Database user data storage
                            Users_data usersData = new Users_data();
                            usersData.setId_User(firebaseUser.getUid());
                            usersData.seteMail(firebaseUser.getEmail());
                            usersData.setUser_Name(firebaseUser.getDisplayName());
                            usersData.setProfile_Pic(firebaseUser.getPhotoUrl().toString());
                            register_database.getReference().child("Restaurants List").child(firebaseUser.getUid()).setValue(usersData);

                            Intent inte = new Intent(Restaurant_Register.this, Screen_Chat.class);
                            startActivity(inte);

                            Toast.makeText(Restaurant_Register.this, "Sign In  Successful using Google", Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }
                    }
                });
    }
}