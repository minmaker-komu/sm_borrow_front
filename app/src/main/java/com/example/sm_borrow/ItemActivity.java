package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_borrow.data.BorrowedItemDto;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<BorrowedItemDto> itemList; // List 타입을 BorrowedItemDto로 변경
    private ApiService apiService;
    private Long memberId; // 사용자 ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);

        // 사용자 ID 가져오기
        memberId = 1L;

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        // Retrofit 초기화
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.5.227:8080/") // 서버의 BASE URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // 서버에서 빌린 물품 데이터 가져오기
        fetchBorrowedItems();

        // 네비게이션 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(ItemActivity.this, ChatActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(ItemActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(ItemActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private void fetchBorrowedItems() {
        apiService.getLentItems(memberId).enqueue(new Callback<List<BorrowedItemDto>>() {
            @Override
            public void onResponse(Call<List<BorrowedItemDto>> call, Response<List<BorrowedItemDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body()); // BorrowedItemDto를 바로 추가
                    adapter.notifyDataSetChanged(); // RecyclerView 업데이트
                } else {
                    Toast.makeText(ItemActivity.this, "서버에서 데이터를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BorrowedItemDto>> call, Throwable t) {
                Toast.makeText(ItemActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ItemActivity", "Error fetching borrowed items", t);
            }
        });
    }
}
