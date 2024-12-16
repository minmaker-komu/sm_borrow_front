package com.example.sm_borrow;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private final Context context;
    private final List<ChatItem> items;

    public ChatListAdapter(Context context, List<ChatItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem item = items.get(position);

        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        holder.itemName.setText(item.getItemName());
        holder.lastMessage.setText(item.getLastMessage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);

            intent.putExtra("ITEM_NAME", item.getItemName());
            intent.putExtra("ITEM_IMAGE_URL", item.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemName, lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
            itemName = itemView.findViewById(R.id.text_item_name);
            lastMessage = itemView.findViewById(R.id.text_last_message);
        }
    }
}
