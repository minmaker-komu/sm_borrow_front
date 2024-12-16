package com.example.sm_borrow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_borrow.data.BorrowedItemDto;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<BorrowedItemDto> itemList;

    public ItemAdapter(List<BorrowedItemDto> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BorrowedItemDto item = itemList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(item.getPrice() + "원");
        holder.itemStatus.setText(item.getBorrowedTime().toString()); // 빌려준 시간 표시

        // 상태에 따라 색상 변경 (예: 시간 기준으로 상태 처리)
        if (item.getBorrowedTime() != null) {
            holder.itemStatus.setTextColor(0xFFFf0000); // 빨간색
        } else {
            holder.itemStatus.setTextColor(0xff00ff00); // 초록색
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemStatus = itemView.findViewById(R.id.itemStatus);
        }
    }
}
