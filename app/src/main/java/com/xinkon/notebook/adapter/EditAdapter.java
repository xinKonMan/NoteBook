package com.xinkon.notebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xinkon.notebook.MainActivity;
import com.xinkon.notebook.R;
import com.xinkon.notebook.activity.EditActivity;
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
    private Context mContext;

    //用于设置点击事件，方便外部调用
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public EditAdapter(Context context,List<Note> notes) {
        this.mContext = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull EditAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(notes.get(position).getTitle());
        holder.tv_content.setText(notes.get(position).getContent());
        holder.tv_time.setText(notes.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(notes.get(position));
            }
        });
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


    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
}
