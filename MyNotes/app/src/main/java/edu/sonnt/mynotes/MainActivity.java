package edu.sonnt.mynotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.List;

import edu.sonnt.mynotes.adapters.NoteAdapter;
import edu.sonnt.mynotes.db.NoteManager;
import edu.sonnt.mynotes.models.Note;

public class MainActivity extends AppCompatActivity {

    public static final int NOTE_ADDED = 1;
    public static final int NOTE_EDITED = 2;

    private RecyclerView rvNotes;
    private NoteManager noteManager;
    private NoteAdapter noteAdapter;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dataset
        noteManager = NoteManager.getInstance(this);
//        noteManager.add(new Note("Example note"));
//        noteManager.add(new Note("Example note 2"));
        notes = noteManager.all();

        // adapter
        noteAdapter = new NoteAdapter(notes);

        // recycler view
        rvNotes = findViewById(R.id.rvNotes);
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        // start Activity for adding new Note

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
//                startActivityForResult(intent, NOTE_ADDED);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && (requestCode == NOTE_ADDED || requestCode == NOTE_EDITED)) {
            // refresh data (load the data from the db again)
            notes.clear();
            notes.addAll(noteManager.all());

            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.menuAddNote){
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, NOTE_ADDED);
        }

        return super.onOptionsItemSelected(item);
    }
}