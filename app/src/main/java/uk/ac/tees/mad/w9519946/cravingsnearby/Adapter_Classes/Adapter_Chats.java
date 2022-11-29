package uk.ac.tees.mad.w9519946.cravingsnearby.Adapter_Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Msg_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.R;

public class Adapter_Chats extends RecyclerView.Adapter{
    //Get values from the Model Class
    ArrayList<Msg_data> msg_data;
    Context context;

    int VIEW_TYPE_SENDER = 3;
    int VIEW_TYPE_RECEIVER = 4;

    //Context creation for Recycler View
    public Adapter_Chats(ArrayList<Msg_data> msg_data, Context context) {
        this.msg_data = msg_data;
        this.context = context;
    }

    @NonNull
    @Override
    //XML to Java Inflate
    //All users data will be shown here in the below layout format...
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENDER){
            View look = LayoutInflater.from(context).inflate(R.layout.sender_sample, parent, false);
            return new ViewHolderSender(look);
        }
        else {
            View look = LayoutInflater.from(context).inflate(R.layout.receiver_sample, parent, false);
            return new ViewHolderReciever(look);
        }
    }

    @Override
    //Identify the view Types
    public int getItemViewType(int location) {
        if (msg_data.get(location).getuID().equals(FirebaseAuth.getInstance().getUid())){
            return VIEW_TYPE_SENDER;
        }
        else {
            return VIEW_TYPE_RECEIVER;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder hanger, int location) {
        Msg_data msg_data1 = msg_data.get(location);
        if (hanger.getClass() == ViewHolderSender.class){
            ((ViewHolderSender)hanger).msg_Sender.setText(msg_data1.getMsg());
        }else {
            ((ViewHolderReciever)hanger).msg_Reciever.setText(msg_data1.getMsg());
        }

    }

    @Override
    //List Count
    public int getItemCount() {
        return msg_data.size();
    }

    //Sender ViewHolder
    public class ViewHolderSender extends RecyclerView.ViewHolder {

        TextView msg_Sender;
        TextView timestamp_Sender;

        public ViewHolderSender(@NonNull View itemView) {
            super(itemView);

            msg_Sender = itemView.findViewById(R.id.text_sender);
            timestamp_Sender = itemView.findViewById(R.id.timestamp_sender);
        }
    }

    //Reciever ViewHolder
    public class ViewHolderReciever extends RecyclerView.ViewHolder {

        TextView msg_Reciever;
        TextView timestamp_Reciever;

        public ViewHolderReciever(@NonNull View itemView) {
            super(itemView);

            msg_Reciever = itemView.findViewById(R.id.text_reciever);
            timestamp_Reciever = itemView.findViewById(R.id.timestamp_reciever);
        }
    }



}
