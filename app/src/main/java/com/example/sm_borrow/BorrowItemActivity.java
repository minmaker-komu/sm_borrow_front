package com.example.sm_borrow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BorrowItemActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBorrowedItems;
    private BorrowItemAdapter adapter;
    private List<BorrowItem> borrowedItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_item);

        recyclerViewBorrowedItems = findViewById(R.id.recyclerViewBorrowedItems);

        // 데이터 초기화 (추후 서버에서 받아올 수 있도록 수정)
        borrowedItemList = new ArrayList<>();
        borrowedItemList.add(new BorrowItem("삼성 노트북 충전기", "500원/시간", "2024-04-01 ~ 2024-04-03"));
        borrowedItemList.add(new BorrowItem("C-Type 충전기", "300원/시간", "2024-04-05 ~ 2024-04-06"));

        // RecyclerView
        adapter = new BorrowItemAdapter(borrowedItemList);
        recyclerViewBorrowedItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBorrowedItems.setAdapter(adapter);
    }
}
