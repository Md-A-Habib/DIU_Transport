package com.diu.transportapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diu.transportapp.R;
import com.diu.transportapp.model.Complaint;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {

    public interface Listener {
        void onDelete(Complaint complaint);
    }

    private final List<Complaint> items;
    private final Listener listener; // null = no delete action
    private final boolean showUserId; // true for admin's "all complaints" view

    public ComplaintAdapter(List<Complaint> items, boolean showUserId, Listener listener) {
        this.items = items;
        this.showUserId = showUserId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint, parent, false);
        return new ComplaintViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = items.get(position);
        holder.tvTitle.setText(complaint.title);
        holder.tvDescription.setText(complaint.description);

        String meta = complaint.createdAt;
        if (showUserId) {
            meta = "User #" + complaint.userId + "  •  " + meta;
        }
        holder.tvMeta.setText(meta);

        holder.btnDelete.setVisibility(listener != null ? View.VISIBLE : View.GONE);
        if (listener != null) {
            holder.btnDelete.setOnClickListener(v -> listener.onDelete(complaint));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvMeta, btnDelete;

        ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvMeta = itemView.findViewById(R.id.tvMeta);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
