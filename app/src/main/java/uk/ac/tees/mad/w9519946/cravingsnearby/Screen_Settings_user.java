package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityScreenSettingsUserBinding;

public class Screen_Settings_user extends AppCompatActivity {

    //Variables and Binding
    FirebaseStorage firebaseStorage_settings;
    FirebaseAuth firebaseAuth_settings;
    FirebaseDatabase firebaseDatabase_settings;
    ActivityScreenSettingsUserBinding screenSettingsUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenSettingsUserBinding = ActivityScreenSettingsUserBinding.inflate(getLayoutInflater());
        setContentView(screenSettingsUserBinding.getRoot());
        getSupportActionBar().hide();

        //Instances
        firebaseAuth_settings = FirebaseAuth.getInstance();
        firebaseStorage_settings = FirebaseStorage.getInstance();
        firebaseDatabase_settings = FirebaseDatabase.getInstance();


        firebaseDatabase_settings.getReference().child("Restaurants List").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users_data users_data = snapshot.getValue(Users_data.class);
                        Picasso.get().load(users_data.getProfile_Pic()).placeholder(R.drawable.user_image).into(screenSettingsUserBinding.imageProfile1);

                        //Settings Screen data change
                        screenSettingsUserBinding.aboutSettingsSettings.setText(users_data.getRestaurant_User_Info());
                        screenSettingsUserBinding.userNameSettings.setText(users_data.getUser_Name());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        screenSettingsUserBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Objects for Hash Map
                String name_User = screenSettingsUserBinding.userNameSettings.getText().toString();
                String info_status = screenSettingsUserBinding.aboutSettingsSettings.getText().toString();
                //Add values in Firebase
                HashMap<String, Object> objectHashMap = new HashMap<>();
                objectHashMap.put("Restaurant_User_Info", info_status);
                objectHashMap.put("user_Name", name_User);

                Toast.makeText(Screen_Settings_user.this, "Profile Updated.", Toast.LENGTH_SHORT).show();
                firebaseDatabase_settings.getReference().child("Restaurants List").child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(objectHashMap);
            }
        });

        //Add Pic
        screenSettingsUserBinding.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent();
                intent_add.setAction(Intent.ACTION_GET_CONTENT);
                intent_add.setType("image/*");
                startActivityForResult(intent_add, 41);
            }
        });

        //Back Arrow
        screenSettingsUserBinding.backSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Screen_Settings_user.this, Screen_Chat.class);
                startActivity(back);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null){

            Uri fileS = data.getData();
            screenSettingsUserBinding.imageProfile1.setImageURI(fileS);

            final StorageReference refS = firebaseStorage_settings.getReference().child("profile_Pic")
                    .child(FirebaseAuth.getInstance().getUid());

            refS.putFile(fileS).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    refS.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(Screen_Settings_user.this, "Successfully Updated the Picture!", Toast.LENGTH_SHORT).show();
                            firebaseDatabase_settings.getReference().child("Restaurants List").child(FirebaseAuth.getInstance().getUid())
                                    .child("profile_Pic").setValue(uri.toString());
                        }
                    });
                }
            });
        }
    }
}