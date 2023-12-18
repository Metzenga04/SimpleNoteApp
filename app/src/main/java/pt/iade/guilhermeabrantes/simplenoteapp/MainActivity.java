    package pt.iade.guilhermeabrantes.simplenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import pt.iade.guilhermeabrantes.simplenoteapp.adapters.NoteItemAdapter;
import pt.iade.guilhermeabrantes.simplenoteapp.models.NoteItem;

    public class MainActivity extends AppCompatActivity {
        protected RecyclerView itemsListView;
        protected NoteItemAdapter itemAdapter;
        protected ArrayList<NoteItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsList = NoteItem.List();
        setupComponents();
    }

        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main,menu);
            return super.onPrepareOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.action_new_item){
                Intent intent =new Intent(MainActivity.this, NoteActivity.class);

                intent.putExtra("item",new NoteItem());

                startActivity(intent);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));

        itemAdapter = new NoteItemAdapter(this, itemsList);
        itemAdapter.setClickListener((new NoteItemAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent =new Intent(MainActivity.this, NoteActivity.class);

                intent.putExtra("item",itemsList.get(position));

                startActivity(intent);
            }
        }));

        itemsListView = (RecyclerView) findViewById(R.id.note_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemAdapter);
    }
}