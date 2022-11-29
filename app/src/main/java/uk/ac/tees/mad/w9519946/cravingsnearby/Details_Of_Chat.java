package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.ActivityDetailsOfChatBinding;

public class Details_Of_Chat extends AppCompatActivity {

    //Variables
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ActivityDetailsOfChatBinding detailsOfChatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsOfChatBinding = ActivityDetailsOfChatBinding.inflate(getLayoutInflater());
        setContentView(detailsOfChatBinding.getRoot());
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String id_Sender = firebaseAuth.getUid();
        String id_Reciever = getIntent().getStringExtra("id_User");
        String user_Name = getIntent().getStringExtra("user_Name");
        String pic_Profile = getIntent().getStringExtra("pic_Profile");

        detailsOfChatBinding.usernameChat.setText(user_Name);
        Picasso.get().load(pic_Profile).placeholder(R.drawable.user_image).into(detailsOfChatBinding.profileImage);

        detailsOfChatBinding.btnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Details_Of_Chat.this, Screen_Chat.class);
                startActivity(inte);
            }
        });

    }
}