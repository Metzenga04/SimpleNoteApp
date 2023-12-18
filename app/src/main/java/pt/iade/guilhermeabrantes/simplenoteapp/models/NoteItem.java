package pt.iade.guilhermeabrantes.simplenoteapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;
    private boolean done;
    private String title;
    private Calendar date;
    private String notes;

    public NoteItem(){
        this(0,false,"",new GregorianCalendar(),"");
    }
    public NoteItem(int id,boolean done, String title, Calendar date, String notes) {
        this.id = id;
        this.done = done;
        this.title =title;
        this.date= date;
        this.notes = notes;
    }

    public static ArrayList<NoteItem> List(){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();

        items.add(new NoteItem(1,false,"First Note item", new GregorianCalendar(),
            "Some discription"));
        items.add(new NoteItem(2,true,"Finish Task", new GregorianCalendar(),
                "Some discription"));
        items.add(new NoteItem(3,false,"A future note", new GregorianCalendar(2023,12,10),
                ""));
        items.add(new NoteItem(4,false,"Play a song", new GregorianCalendar(23,12,24),
                "siuuuuuuuuu"));

        return items;

    }
    public static NoteItem GetById(int id){
        return new NoteItem(id,false,"yeah",new GregorianCalendar(),"pois e");
    }
    public void save() {
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
        } else {

        }
    }
    public int getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
