package edu.sonnt.myamin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView character;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set default display character
        character = findViewById(R.id.star);

        //spin
        Button btnSpin = findViewById(R.id.btnSpin);
        btnSpin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                character.animate().rotationBy(360*2).setDuration(2000);
            }
        });

        //turn around
        Button btnTurnAround = findViewById(R.id.btnTurnAround);
        btnTurnAround.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                character.animate().rotationYBy(360*2).setDuration(2000);
            }
        });

        //jump
        Button btnJump = findViewById(R.id.btnJump);
        btnJump.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                character.animate().translationYBy(-200).setDuration(1000).withEndAction(new Runnable(){
                    @Override
                    public void run() {
                        character.animate().translationYBy(200).setDuration(1000);
                    }
                });
            }
        });

        //clap
        //MediaPlayer mediaPlayer = MediaPlayer.create( MainActivity.this, R.raw.clapping);
        Button btnClap = findViewById(R.id.btnClap);
        btnClap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //mediaPlayer.reset();
                MediaPlayer mediaPlayer = MediaPlayer.create( MainActivity.this, R.raw.clapping);
                mediaPlayer.start();
            }
        });
    }



    public void onCharacterClick(View view){
        if (view == findViewById(R.id.btnStar)){
            character = findViewById(R.id.star);
            //show star, hide ball
            findViewById(R.id.star).animate().alpha(1).setDuration(1000);
            findViewById(R.id.ball).animate().alpha(0).setDuration(1000);
        } else if (view == findViewById(R.id.btnBall)){
            character = findViewById(R.id.ball);
            findViewById(R.id.ball).animate().alpha(1).setDuration(1000);
            findViewById(R.id.star).animate().alpha(0).setDuration(1000);
        }
    }
}