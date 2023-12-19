package pt.iade.guilhermeabrantes.simplenoteapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String creationDate;
    private String content;
    private String modifiedDate;

    public NoteItem(){
        this(0,"","20/10/23","","");
    }
    public NoteItem(int id, String title,String creationDate, String notes,String modifiedDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
        this.modifiedDate= modifiedDate;
        this.content = notes;
    }

    public static ArrayList<NoteItem> List(){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();

        items.add(new NoteItem(1,"First Note item","10/12/23","some notes here!!!", ""));
        items.add(new NoteItem(2,"Future Note","2/09/23","", "Some good things"));
        items.add(new NoteItem(3,"Nice one note", "4/05/23","", ""));
        items.add(new NoteItem(4,"Play a song","16/09/23","", "siuuuuuuuuu"));

        return items;

    }
    public static NoteItem GetById(int id){
        return new NoteItem(id,"yeah","9/5/23","","pois e");
    }
    public void save() {
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
        } else {

        }
    }
    public void delete(){
        if(id == 0){
            id =  new Random().nextInt(1000) + 1;
        }
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
