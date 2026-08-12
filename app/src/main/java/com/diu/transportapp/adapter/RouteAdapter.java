package com.diu.transportapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diu.transportapp.R;
import com.diu.transportapp.model.BusRoute;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    public interface Listener {
        void onEdit(BusRoute route);
        void onDelete(BusRoute route);
    }

    private final List<BusRoute> items;
    private final Listener listener; // null = read-only mode

    public RouteAdapter(List<BusRoute> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        BusRoute route = items.get(position);
        holder.tvRouteName.setText(route.routeName);
        holder.tvLocations.setText(route.startLocation + "  →  " + route.endLocation);
        holder.tvTime.setText("Departure: " + route.departureTime);

        boolean adminMode = listener != null;
        holder.btnEdit.setVisibility(adminMode ? View.VISIBLE : View.GONE);
        holder.btnDelete.setVisibility(adminMode ? View.VISIBLE : View.GONE);

        if (adminMode) {
            holder.btnEdit.setOnClickListener(v -> listener.onEdit(route));
            holder.btnDelete.setOnClickListener(v -> listener.onDelete(route));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RouteViewHolder extends RecyclerView.ViewHolder {
        TextView tvRouteName, tvLocations, tvTime, btnEdit, btnDelete;

        RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRouteName = itemView.findViewById(R.id.tvRouteName);
            tvLocations = itemView.findViewById(R.id.tvLocations);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
