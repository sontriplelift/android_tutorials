package com.example.mytube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private int currentPlaybackPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //retrieve the link to video from Intent
        Intent intent = getIntent();
        String key = intent.getStringExtra("LINK");

        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(key);
        //control the video
        videoView.setMediaController(new MediaController(this));
        //videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //store current playback position
        currentPlaybackPosition = videoView.getCurrentPosition();
        Log.d("abc", currentPlaybackPosition+"");
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("abc-d", currentPlaybackPosition+"");

        outState.putInt("PLAYBACK", currentPlaybackPosition);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //restore current playback position
        int currentPlaybackPositionInMillisec = savedInstanceState.getInt("PLAYBACK");
        videoView.seekTo(currentPlaybackPositionInMillisec);
    }
}