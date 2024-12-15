package com.example.sm_borrow;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 더미 데이터
        itemList = new ArrayList<>();
        itemList.add(new Item("c-type 충전기", "500원/시간", "대여 중"));
        itemList.add(new Item("삼성 노트북 충전기", "500원/시간", "대여 가능"));
        itemList.add(new Item("애플 충전기", "300원/시간", "대여 중"));

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
