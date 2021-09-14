package edu.sonnt.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

// create and upgrade db
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        db.execSQL("CREATE TABLE " + DbSchema.NotesTable.NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.NotesTable.Cols.TEXT + " TEXT"+")"
        );

        // other tables here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w("My Notes", "My Notes: upgrading DB; dropping/recreating tables.");
        // drop existing tables
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.NotesTable.NAME);

        // create tables again
        onCreate(db);
    }
}
