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

// 빌린 물품조회
public class BorrowItemActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBorrowedItems;
    private BorrowItemAdapter adapter;
    private List<BorrowedItemDto> borrowedItemList;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_item);

        recyclerViewBorrowedItems = findViewById(R.id.recyclerViewBorrowedItems);

        // Retrofit 초기화
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.5.39:8080/") // 서버의 BASE URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // RecyclerView 초기화
        borrowedItemList = new ArrayList<>();
        adapter = new BorrowItemAdapter(borrowedItemList);
        recyclerViewBorrowedItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBorrowedItems.setAdapter(adapter);

        // 서버에서 빌린 물품 데이터 가져오기
        fetchBorrowedItems();

        // 네비게이션 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(BorrowItemActivity.this, ChatActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(BorrowItemActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(BorrowItemActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private void fetchBorrowedItems() {
//        Long userId = getIntent().getLongExtra("MEMBER_ID", -1);
        Long userId = 1L; // 임시로 사용자 ID를 1로 설정

        apiService.getBorrowedItems(userId).enqueue(new Callback<List<BorrowedItemDto>>() {
            @Override
            public void onResponse(Call<List<BorrowedItemDto>> call, Response<List<BorrowedItemDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    borrowedItemList.clear();
                    borrowedItemList.addAll(response.body());
                    adapter.notifyDataSetChanged(); // RecyclerView 업데이트
                } else {
                    Toast.makeText(BorrowItemActivity.this, "서버에서 데이터를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BorrowedItemDto>> call, Throwable t) {
                Toast.makeText(BorrowItemActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("BorrowItemActivity", "Error fetching borrowed items", t);
            }
        });
    }
}
