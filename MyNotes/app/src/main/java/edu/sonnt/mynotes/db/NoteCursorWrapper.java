package edu.sonnt.mynotes.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import edu.sonnt.mynotes.models.Note;

public class NoteCursorWrapper extends CursorWrapper {

    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        long id = getLong(getColumnIndex("id"));
        String text = getString(getColumnIndex(DbSchema.NotesTable.Cols.TEXT));

        Note note = new Note(id, text);
        return note;
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        moveToFirst();
        while (!isAfterLast()) {
            Note note = getNote();
            notes.add(note);
            moveToNext();
        }
        return notes;
    }

    public Note getNoteById(){
        Note note = null;

        moveToFirst();
        if (!isAfterLast()){
            note = getNote();
        }
        return note;
    }
}
