package edu.sonnt.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.sonnt.mynotes.db.NoteManager;
import edu.sonnt.mynotes.models.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText addText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addText = findViewById(R.id.addText);
    }

    @Override
    public void onBackPressed() {
        String text = addText.getText().toString();
        Note note = new Note(text);
        NoteManager.getInstance(this).add(note);
        setResult(this.RESULT_OK);

        super.onBackPressed();
    }

    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnOK:
//                EditText edtText = findViewById(R.id.edtText);
//                String text = edtText.getText().toString();
//
//                // validate data
//                if (text.equals("")) {
//                    Toast.makeText(this, "Missing text!", Toast.LENGTH_LONG).show();
//                } else {
//                    Note note = new Note(text);
//
//                    NoteManager noteManager = NoteManager.getInstance(this);
//                    noteManager.add(note);
//                    Toast.makeText(this, "Completed!", Toast.LENGTH_LONG).show();
//
//                    setResult(RESULT_OK);
//                    finish();
//                }
//
//            break;
//
//            case R.id.btnCancel:
//                setResult(RESULT_CANCELED);
//                finish();
//                break;
//        }
    }
}