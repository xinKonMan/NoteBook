package com.xinkon.notebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xinkon.notebook.R;
import com.xinkon.notebook.entity.Note;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @author xinkonman
 * @desription:
 * @date: 2022/4/20 16:27
 */

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.ViewHolder> {


    private List<Note> notes;

    public EditAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false);
        return new ViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull EditAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(notes.get(position).getTitle());
        holder.tv_content.setText(notes.get(position).getContent());
        holder.tv_time.setText(notes.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_content;
        TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
