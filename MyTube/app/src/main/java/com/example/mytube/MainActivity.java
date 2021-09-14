package com.example.mytube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtLink = findViewById(R.id.edtLink);
                String link = edtLink.getText().toString();

                //start PlayerActivity
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                //pass data
                intent.putExtra("LINK", link);
                startActivity(intent);
            }
        });
    }
}