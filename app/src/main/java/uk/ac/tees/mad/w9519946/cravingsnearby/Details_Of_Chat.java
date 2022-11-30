package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Chats;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Msg_data;
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

        //Set Hooks and Connect Firebase..
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final String id_Sender = firebaseAuth.getUid();
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

        //Set Array List
        final ArrayList<Msg_data> msg_data = new ArrayList<>();

        //Set adapter
        final Adapter_Chats adapter_chats = new Adapter_Chats(msg_data, id_Reciever, this);
        detailsOfChatBinding.recyclerViewChat.setAdapter(adapter_chats);

        //Linear Layout to put chat in line form...
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        detailsOfChatBinding.recyclerViewChat.setLayoutManager(linearLayoutManager);

        //Button Sent
        final String roomreceiver = id_Reciever + id_Sender;
        final String roomSender = id_Sender + id_Reciever;

        firebaseDatabase.getReference().child("Application Chats").child(roomSender)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            //Adding of data to Recyclerview in Details of Chat screen...
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                msg_data.clear();
                                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                                    Msg_data msgData1 = datasnapshot.getValue(Msg_data.class);
                                    msgData1.setId_Msg(datasnapshot.getKey());
                                    msg_data.add(msgData1);

                                }
                                adapter_chats.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        detailsOfChatBinding.btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (detailsOfChatBinding.chatMessage.getText().toString().isEmpty()){
                    detailsOfChatBinding.chatMessage.setError("Cannot leave it blank!");
                    return;
                }
                String msg = detailsOfChatBinding.chatMessage.getText().toString();
                final Msg_data msg_data1 = new Msg_data(msg, id_Sender);
                msg_data1.setTime_Stamp(new Date().getTime());
                detailsOfChatBinding.chatMessage.setText("");

                firebaseDatabase.getReference().child("Application Chats").child(roomSender)
                        .push().setValue(msg_data1).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                firebaseDatabase.getReference().child("Application Chats")
                                        .child(roomreceiver).push()
                                        .setValue(msg_data1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });

            }
        });


    }
}