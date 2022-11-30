package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Details_Of_Chat;
import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Users_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_Users extends  RecyclerView.Adapter<Adapter_Users.vview_Holder>{
    //Get values from the Model Class
    ArrayList<Users_data> values;
    //Context creation for Recycler View
    Context content;
    public Adapter_Users(ArrayList<Users_data> values, Context content) {
        this.values = values;
        this.content = content;
    }

    @NonNull
    @Override
    //All users data will be shown here in the below layout format...
    public vview_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(content).inflate(R.layout.user_details, parent, false);
        return new vview_Holder(holder);
    }

    @Override
    //Defining the data from where the users are getting the data...
    public void onBindViewHolder(@NonNull vview_Holder hanger, int position) {
        Users_data data = values.get(position);

        //Cronology of the data taken is decided here..
        //Image
        Picasso.get().load(data.getProfile_Pic()).placeholder(R.drawable.user_avatar).into(hanger.pic_Profile);

        //UserName
        hanger.name_User.setText(data.getUser_Name());

        FirebaseDatabase.getInstance().getReference().child("Application Chats")
                .child(FirebaseAuth.getInstance().getUid()+ data.getId_User())
                        .orderByChild("time_Stamp").limitToLast(1)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChildren()){
                                    for (DataSnapshot shot:snapshot.getChildren()){
                                        hanger.last_msg.setText(shot.child("msg").getValue(String.class));

                                    }
                                }
                            }
                        });

        //User name and Image sent to next chat screen.
        hanger.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(content, Details_Of_Chat.class);
                inte.putExtra("pic_Profile", data.getProfile_Pic());
                inte.putExtra("user_Name", data.getUser_Name());
                inte.putExtra("id_User", data.getId_User());
                content.startActivity(inte);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    //Create a View Holder that will be iterated in Recycler View...
    public class vview_Holder extends RecyclerView.ViewHolder{

        TextView name_User;
        TextView last_msg;
        ImageView pic_Profile;

        public vview_Holder(@NonNull View itemView) {
            super(itemView);

            pic_Profile = itemView.findViewById(R.id.pic_Profile);
            name_User = itemView.findViewById(R.id.name_Of_User);
            last_msg = itemView.findViewById(R.id.end_Msg);
        }
    }

}
