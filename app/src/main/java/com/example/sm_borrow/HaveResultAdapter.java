package com.example.sm_borrow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HaveResultAdapter extends RecyclerView.Adapter<HaveResultAdapter.ResultViewHolder> {

    private final List<String> items;
    private final Context context; // Context 추가
    private final Long memberId; // MEMBER_ID 추가

    public HaveResultAdapter(Context context, List<String> items, Long memberId) {
        this.context = context; // Context 저장
        this.items = items;
        this.memberId = memberId; // MEMBER_ID 저장
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        String item = items.get(position);
        holder.button.setText(item);

        // 버튼 클릭 이벤트 추가
        holder.button.setOnClickListener(v -> {
            Intent intent = new Intent(context, PriceSelection.class);
            intent.putExtra("BUTTON_INFO", item); // 버튼 정보를 전달
            intent.putExtra("MEMBER_ID", memberId); // MEMBER_ID 전달
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.item_button);
        }
    }
}
