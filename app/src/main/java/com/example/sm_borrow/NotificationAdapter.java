package com.example.sm_borrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_borrow.data.AlertDto;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context context;
    private final List<AlertDto> alertList;
    private final OnActionListener actionListener;

    public interface OnActionListener {
        void onAction(AlertDto alert, String status);
    }

    public NotificationAdapter(Context context, List<AlertDto> alertList, OnActionListener actionListener) {
        this.context = context;
        this.alertList = alertList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlertDto alert = alertList.get(position);

        holder.itemName.setText("아이템 ID: " + alert.getItemId());
        holder.status.setText("상태: " + alert.getStatus());
        holder.createdAt.setText("생성일: " + alert.getCreatedAt());

        holder.acceptButton.setOnClickListener(v -> actionListener.onAction(alert, "승인"));
        holder.rejectButton.setOnClickListener(v -> actionListener.onAction(alert, "거절"));
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, status, createdAt;
        Button acceptButton, rejectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_item_name);
            status = itemView.findViewById(R.id.text_status);
            //createdAt = itemView.findViewById(R.id.text_created_at);
            acceptButton = itemView.findViewById(R.id.button_accept);
            rejectButton = itemView.findViewById(R.id.button_reject);
        }
    }
}
