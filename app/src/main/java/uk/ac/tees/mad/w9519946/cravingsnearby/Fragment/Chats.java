package uk.ac.tees.mad.w9519946.cravingsnearby.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes.Adapter_Users;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;
import uk.ac.tees.mad.w9519946.cravingsnearby.databinding.FragmentChatsBinding;

public class Chats extends Fragment {
    //Database SetUp
    FirebaseDatabase firebaseDatabase;
    //Binding
    FragmentChatsBinding fragmentChatsBinding;
    //Array List
    ArrayList<Users_data> usersArrayList = new ArrayList<>();


    public Chats() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentChatsBinding = FragmentChatsBinding.inflate(inflater, container, false);

        //Adapter Setup
        Adapter_Users adapter_users = new Adapter_Users(usersArrayList, getContext());
        fragmentChatsBinding.recyclerViewChat.setAdapter(adapter_users);

        //Layout Manager setup
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragmentChatsBinding.recyclerViewChat.setLayoutManager(linearLayoutManager);

        //Database data setup, hooks
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Restaurants List").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clears the list before loading the data.
                usersArrayList.clear();

                for (DataSnapshot snapshot_data : snapshot.getChildren()){
                    Users_data usersData = snapshot_data.getValue(Users_data.class);
                    usersData.setId_User(snapshot_data.getKey());

                        usersArrayList.add(usersData);
                }
                adapter_users.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return fragmentChatsBinding.getRoot();
    }
}