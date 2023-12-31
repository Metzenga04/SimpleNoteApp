    package pt.iade.guilhermeabrantes.simplenoteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
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
                intent.putExtra("position", -1);
                intent.putExtra("item",new NoteItem());

                startActivityForResult(intent,EDITOR_ACTIVITY_RETURN_ID);

                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == EDITOR_ACTIVITY_RETURN_ID){
                if (requestCode == AppCompatActivity.RESULT_OK){
                    int position = data.getIntExtra("position",-1);
                    NoteItem updatedItem = (NoteItem) data.getSerializableExtra("item");

                    if (position == -1){
                        itemsList.add(updatedItem);
                        itemAdapter.notifyItemInserted(itemsList.size() - 1);
                    }else {
                        itemsList.set(position,updatedItem);
                        itemAdapter.notifyItemChanged(position);
                    }
                }
            }
        }

        private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));

        itemAdapter = new NoteItemAdapter(this, itemsList);
        itemAdapter.setClickListener((new NoteItemAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent =new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("item",itemsList.get(position));

                startActivityForResult(intent,EDITOR_ACTIVITY_RETURN_ID);
            }
        }));

        itemsListView = (RecyclerView) findViewById(R.id.note_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemAdapter);
    }
}