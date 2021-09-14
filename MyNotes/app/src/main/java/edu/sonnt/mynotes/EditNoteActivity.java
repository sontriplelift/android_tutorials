package edu.sonnt.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import edu.sonnt.mynotes.db.NoteManager;
import edu.sonnt.mynotes.models.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText edtText;
    private Note note;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        edtText = findViewById(R.id.edtText);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID", -1);
        NoteManager noteManager = NoteManager.getInstance(this);
        note = noteManager.findById(id);
        edtText.setText(note.getText());
    }

    @Override
    public void onBackPressed() {
        note.setText(edtText.getText().toString());
        note.setId(id);
        NoteManager.getInstance(this).update(note);
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}