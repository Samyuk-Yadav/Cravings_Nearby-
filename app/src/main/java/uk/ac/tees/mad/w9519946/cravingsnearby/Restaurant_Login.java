package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityRestaurantLoginBinding;

public class Restaurant_Login extends AppCompatActivity {
    //Variables
    ActivityRestaurantLoginBinding loginBinding;
    FirebaseAuth firebase1Auth;
    FirebaseDatabase firebaseDatabase;
    GoogleSignInClient userLogin;
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
        firebaseDatabase = FirebaseDatabase.getInstance();

        //SignIn using Google
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        userLogin = GoogleSignIn.getClient(this, options);


        //Google Authentication
        loginBinding.buttonGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });


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

    int RC_Log_In = 84;
    private void logIn() {
        Intent signInIntent = userLogin.getSignInIntent();
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
        firebase1Auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser firebaseUser = firebase1Auth.getCurrentUser();

                            //Firebase Database user data storage
                            Users_data usersData = new Users_data();
                            usersData.setId_User(firebaseUser.getUid());
                            usersData.seteMail(firebaseUser.getEmail());
                            usersData.setUser_Name(firebaseUser.getDisplayName());
                            usersData.setProfile_Pic(firebaseUser.getPhotoUrl().toString());
                            firebaseDatabase.getReference().child("Restaurants List").child(firebaseUser.getUid()).setValue(usersData);

                            Intent inte = new Intent(Restaurant_Login.this, Screen_Chat.class);
                            startActivity(inte);

                            Toast.makeText(Restaurant_Login.this, "Sign In successful using Google", Toast.LENGTH_SHORT).show();
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