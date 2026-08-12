package com.diu.transportapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diu.transportapp.R;
import com.diu.transportapp.model.Notice;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    public interface Listener {
        void onDelete(Notice notice);
    }

    private final List<Notice> items;
    private final Listener listener; // null = read-only mode

    public NoticeAdapter(List<Notice> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = items.get(position);
        holder.tvTitle.setText(notice.title);
        holder.tvDescription.setText(notice.description);
        holder.tvDate.setText(notice.createdAt);

        boolean adminMode = listener != null;
        holder.btnDelete.setVisibility(adminMode ? View.VISIBLE : View.GONE);
        if (adminMode) {
            holder.btnDelete.setOnClickListener(v -> listener.onDelete(notice));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate, btnDelete;

        NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
