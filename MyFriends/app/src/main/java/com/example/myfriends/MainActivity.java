package com.example.myfriends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myfriends.adapters.FriendAdapter;
import com.example.myfriends.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int FRIEND_ADDED = 1;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recycler view
        RecyclerView rvFriends = findViewById(R.id.rvFriends);

        // demo data
        friends = new ArrayList<>();
        friends.add(new Friend("Sontriplelift", "1801040181@s.hanu.edu.vn", "038.2466.527"));
        friends.add(new Friend("Thanhche4x", "1801040181@s.hanu.edu.vn", "038.2466.527"));
        friends.add(new Friend("Toilason", "1801040181@s.hanu.edu.vn", "038.2466.527"));
        friends.add(new Friend("ThanhTien", "1801040181@s.hanu.edu.vn", "038.2466.527"));

        // set up recycler view
        // adapter
        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);
        // layout manager
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        // handle add action
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADDED);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FRIEND_ADDED) {
            Friend friend = (Friend) data.getSerializableExtra("FRIEND");
            friends.add(0, friend);

            // notify the adapter to update recycle view
            //friendAdapter.notifyDataSetChanged();
            friendAdapter.notifyItemInserted(0);
        }
    }
}