package edu.sonnt.mynotes.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sonnt.mynotes.EditNoteActivity;
import edu.sonnt.mynotes.MainActivity;
import edu.sonnt.mynotes.R;
import edu.sonnt.mynotes.db.NoteManager;
import edu.sonnt.mynotes.models.Note;


public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes;
    private Context context;
    private static final int NOTE_EDITED = 2;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Activity to display
        context = parent.getContext();

        // xml to java object refs
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.note, parent, false);

        return new NoteHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, final int position) {
        Note note = notes.get(position);

        // bind holder with view template
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private TextView tvNote;
        private Context context;

        public NoteHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvNote = itemView.findViewById(R.id.tvNote);
            this.context = context;
        }

        public void bind(final Note note) {
            tvNote.setText(note.getText());

            tvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditNoteActivity.class);
                    intent.putExtra("ID", note.getId());
                    intent.putExtra("TEXT", note.getText());
                    ((Activity)context).startActivityForResult(intent, NOTE_EDITED);
                }
            });

            tvNote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Do you want to delete this note?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    notes.remove(note);
                                    NoteManager.getInstance(context).delete(note.getId());
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                    return true;
                }
            });
        }
    }
}
