package pt.iade.guilhermeabrantes.simplenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import pt.iade.guilhermeabrantes.simplenoteapp.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected NoteItem item;
    protected EditText titleEdit;
    protected CheckBox doneCheck;
    protected CalendarView calendar;
    protected EditText notesEdit;
    protected int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        item = (NoteItem) intent.getSerializableExtra("item");

        setupComponents();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note,menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save_item){
            commitView();
            this.item.save();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("position",this.listPosition);

            returnIntent.putExtra("item",this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));
        titleEdit =(EditText) findViewById(R.id.title_edit);
        doneCheck =(CheckBox) findViewById(R.id.done_check);
        calendar = (CalendarView) findViewById(R.id.calendar);
        notesEdit = (EditText) findViewById(R.id.notes_edit);
        populateview();
    }
    protected void populateview(){
        titleEdit.setText(item.getTitle());
        doneCheck.setChecked(item.isDone());
        calendar.setDate(item.getDate().getTimeInMillis());
        notesEdit.setText(item.getNotes());
    }
    protected void commitView(){
        item.setTitle(titleEdit.getText().toString());
        item.setDone(doneCheck.isChecked());
        item.setNotes(notesEdit.getText().toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.calendar.getDate());
        item.setDate(calendar);
    }
}