package edu.sonnt.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConvert = findViewById(R.id.btnConvert);
        EditText amount = findViewById(R.id.inputBox);

        btnConvert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,String.valueOf(Integer.parseInt(amount.getText().toString())*23010),Toast.LENGTH_LONG).show();
                
            }
        });

    }


}