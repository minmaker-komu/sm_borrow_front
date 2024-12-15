package com.example.sm_borrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context context;
    private final List<NotificationItem> items;
    private final OnItemActionListener listener;

    public NotificationAdapter(Context context, List<NotificationItem> items, OnItemActionListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem item = items.get(position);

        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(item.getItemPrice());
        holder.statusMessage.setText(item.getStatusMessage());

        // 이미지 로드 (Glide 사용)
        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground) // 기본 이미지
                .into(holder.itemImage);

        holder.acceptButton.setOnClickListener(v -> listener.onAccept(item, holder));
        holder.rejectButton.setOnClickListener(v -> listener.onReject(item, holder));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemPrice, statusMessage;
        Button acceptButton, rejectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_item);
            itemName = itemView.findViewById(R.id.text_item_name);
            itemPrice = itemView.findViewById(R.id.text_item_price);
            statusMessage = itemView.findViewById(R.id.text_status);
            acceptButton = itemView.findViewById(R.id.button_accept);
            rejectButton = itemView.findViewById(R.id.button_reject);
        }
    }

    public interface OnItemActionListener {
        void onAccept(NotificationItem item, ViewHolder holder);
        void onReject(NotificationItem item, ViewHolder holder);
    }
}

