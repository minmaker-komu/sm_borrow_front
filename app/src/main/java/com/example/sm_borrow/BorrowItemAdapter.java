package com.example.sm_borrow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BorrowItemAdapter extends RecyclerView.Adapter<BorrowItemAdapter.ViewHolder> {

    private List<BorrowItem> borrowedItemList;

    public BorrowItemAdapter(List<BorrowItem> borrowedItemList) {
        this.borrowedItemList = borrowedItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BorrowItem item = borrowedItemList.get(position);
        holder.text1.setText(item.getItemName());
        holder.text2.setText("가격: " + item.getPrice() + " | 기간: " + item.getRentalPeriod());
    }

    @Override
    public int getItemCount() {
        return borrowedItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
