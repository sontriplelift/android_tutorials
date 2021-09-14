package edu.sonnt.mynotes.models;

public class Note {

    private long id;
    private String text;

    // data from db
    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    // data from form
    public Note(String text){
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
