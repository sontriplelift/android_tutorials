package com.example.myfriends.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfriends.R;
import com.example.myfriends.models.Friend;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    public static final int FRIEND_EDITED = 1;
    //dataset
    private List<Friend> friends;

    public FriendAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // activity to display
        Context context = parent.getContext();
        // layout inflater xml ->> java object refs
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_friend, parent, false);
        return new FriendHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend friend = friends.get(position);
        //bind data with view template
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageButton btnCall, btnSendSMS, btnSendEmail, btnEdit, btnDelete;
        private Context context;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvName = itemView.findViewById(R.id.tvName);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnSendSMS = itemView.findViewById(R.id.btnSendSMS);
            btnSendEmail = itemView.findViewById(R.id.btnSendEmail);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Friend friend){
            tvName.setText(friend.getName());
            // handle events
            // telephony
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });
            // send sms
            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"+friend.getPhoneNo()));
                    context.startActivity(intent);
                }
            });

            // send email
            btnSendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+Uri.encode(friend.getEmail())));
                    context.startActivity(intent);
                }
            });

            // delete friend
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = friends.indexOf(friend);
                    friends.remove(friend);
                    // notify recycler view to update with changes
                    //notifyDataSetChanged();
                    // optimize
                    notifyItemRemoved(position);
                }
            });

            // edit friend

        }

    }
}
