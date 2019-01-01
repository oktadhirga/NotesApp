package com.example.aya.mynotesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aya.mynotesapp.CustomOnItemClickListener;
import com.example.aya.mynotesapp.FormAddUpdateActivity;
import com.example.aya.mynotesapp.R;
import com.example.aya.mynotesapp.entity.Note;

import java.util.LinkedList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private LinkedList<Note> listNote;
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<Note> getListNote() {
        return listNote;
    }

    public void setListNote(LinkedList<Note> listNote) {
        this.listNote = listNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tvTitle.setText(getListNote().get(position).getTitle());
        holder.tvDescription.setText(getListNote().get(position).getDescription());
        holder.tvDate.setText(getListNote().get(position).getDate());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNote().get(position));
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);

            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListNote().size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDate, tvDescription;
        CardView cvNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
