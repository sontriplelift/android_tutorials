package com.example.myfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myfriends.models.Friend;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ImageButton btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtName = findViewById(R.id.edtName);
                EditText edtEmail = findViewById(R.id.edtEmail);
                EditText edtPhoneNo = findViewById(R.id.edtPhoneNo);
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phoneNo = edtPhoneNo.getText().toString();

                Friend friend = new Friend(name, email, phoneNo);

                Intent intent = new Intent();
                intent.putExtra("FRIEND", friend);

                setResult(RESULT_OK, intent);
                // finish current activity and back to the previous one
                finish();
            }
        });

        ImageButton btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}