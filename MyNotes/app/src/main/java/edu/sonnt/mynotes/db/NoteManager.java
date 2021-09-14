package edu.sonnt.mynotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import edu.sonnt.mynotes.models.Note;

public class NoteManager {

    // singleton
    private static NoteManager instance;
    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.NotesTable.NAME + "(text) VALUES (?)";
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    public static NoteManager getInstance(Context context) {
        if (instance == null) {
            instance = new NoteManager(context);
        }
        return instance;
    }
    private NoteManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase(); // trigger onCreate if it not exist / onUpgrade
    }

    public List<Note> all() {
        String sql = "SELECT * FROM " + DbSchema.NotesTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);
        return cursorWrapper.getNotes();
    }

    public Note findById(long id){
        String sql = "SELECT * FROM " + DbSchema.NotesTable.NAME + " WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id+""});

        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);

        return cursorWrapper.getNoteById();
    }

    /**
     * @modifies note
     */
    public boolean add(Note note) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);
        statement.bindString(1, note.getText());

        long id = statement.executeInsert(); // auto generated id
        if (id > 0) { // insert success
            note.setId(id);
            return true;
        }
        return false;
    }

    public boolean delete(long id) {
        int result = db.delete(DbSchema.NotesTable.NAME, "id = ?", new String[]{ id+"" });
        return result > 0;
    }

    public boolean update(Note note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEXT", note.getText());
        int result = db.update(DbSchema.NotesTable.NAME, contentValues, "id = ?", new String[]{note.getId()+""});
        return result > 0;
    }
}
