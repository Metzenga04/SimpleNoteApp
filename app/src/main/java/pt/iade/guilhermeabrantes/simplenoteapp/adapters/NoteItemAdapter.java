package pt.iade.guilhermeabrantes.simplenoteapp.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.guilhermeabrantes.simplenoteapp.R;
import pt.iade.guilhermeabrantes.simplenoteapp.models.NoteItem;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {
    private final ArrayList<NoteItem> items;
    private final LayoutInflater inflater;
    private ItemClickListener clickListener;

    public NoteItemAdapter(Context context,ArrayList<NoteItem> items){
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setClickListener(ItemClickListener listener)    {
        clickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.row_note_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        NoteItem item = items.get(position);

        holder.titleLabel.setText(item.getTitle());
        holder.notesLabel.setText(item.getContent());

    }
    @Override
    public int getItemCount(){
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleLabel;
        public TextView notesLabel;
        public TextView dateLabel;
        public CheckBox doneCheck;

        public ViewHolder(View itemView){
            super(itemView);
            titleLabel = itemView.findViewById(R.id.title_label);
            notesLabel = itemView.findViewById(R.id.notes_label);
            dateLabel = itemView.findViewById(R.id.date_label);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());

        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
