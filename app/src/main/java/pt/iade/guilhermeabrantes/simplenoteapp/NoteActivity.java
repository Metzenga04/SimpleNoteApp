package pt.iade.guilhermeabrantes.simplenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import pt.iade.guilhermeabrantes.simplenoteapp.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected NoteItem item;
    protected EditText titleEdit;
    protected CheckBox doneCheck;
    protected CalendarView calendar;
    protected EditText creationDate;
    protected String modifiedDate;
    protected TextView content;
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
        doneCheck.setVisibility(View.GONE);
        creationDate = (EditText) findViewById(R.id.creationDate);
        calendar = (CalendarView) findViewById(R.id.calendar);
        content = (EditText) findViewById(R.id.notes_edit);
        populateview();
        calendar.setVisibility(View.GONE);
    }
    protected void populateview(){
        titleEdit.setText(item.getTitle());
        content.setText(item.getContent());
        creationDate.setText(item.getCreationDate());

        calendar.setVisibility(View.GONE);
    }
    protected void commitView(){
        calendar.setVisibility(View.GONE);
        doneCheck.setVisibility(View.GONE);
        item.setTitle(titleEdit.getText().toString());
        item.setContent(content.getText().toString());
        item.setCreationDate(creationDate.getText().toString());

    }
}