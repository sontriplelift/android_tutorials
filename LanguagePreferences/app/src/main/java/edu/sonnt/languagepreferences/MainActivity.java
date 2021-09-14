package edu.sonnt.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String P_LANG = "LANG";

    private SharedPreferences sharedPreferences;
    private TextView tvGreeting;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //widget references
        tvGreeting = findViewById(R.id.tvGreeting);

        // check for language preference
        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String language = sharedPreferences.getString(P_LANG, null);

        //if it already set
        if (language != null) {
            setLanguage(language);
        } else {
            // display the dialog for language
            selectLanguage();
        }

        setContentView(R.layout.activity_main);

    }

    /**
     * @effects display the dialog
     */
    public void selectLanguage(){
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("vi");
                        recreate();
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("en");
                        recreate();
                    }
                })
                .setNeutralButton("Japanese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("ja");
                        recreate();
                    }
                })
                .show();
    }

    /**
     * @effects
     *  update the language preference
     *  update te textview with the special language
     */
    public void setLanguage(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //update the language preference
        sharedPreferences.edit().putString(P_LANG, lang).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuLangEn:
                setLanguage("en");
                recreate();
                break;
            case R.id.menuLangVi:
                setLanguage("vi");
                recreate();
                break;
            case R.id.menuLangJa:
                setLanguage("ja");
                recreate();
                break;
            case R.id.menuClear:
                //sharedPreferences.edit().putString(P_LANG, null).apply();
                sharedPreferences.edit().remove(P_LANG).apply(); // remove language preferences
                //sharedPreferences.edit().clear().apply(); // clear all the preferences
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}