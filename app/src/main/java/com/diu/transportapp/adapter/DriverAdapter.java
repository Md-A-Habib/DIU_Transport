package com.diu.transportapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diu.transportapp.R;
import com.diu.transportapp.model.UserResponse;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    public interface Listener {
        void onDelete(UserResponse driver);
    }

    private final List<UserResponse> items;
    private final Listener listener;

    public DriverAdapter(List<UserResponse> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver, parent, false);
        return new DriverViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        UserResponse driver = items.get(position);
        holder.tvName.setText(driver.fullName);
        holder.tvContact.setText(driver.email + "  •  " + driver.phone);
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(driver);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DriverViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContact, btnDelete;

        DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvContact = itemView.findViewById(R.id.tvContact);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
