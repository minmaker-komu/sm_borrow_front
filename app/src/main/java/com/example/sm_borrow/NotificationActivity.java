package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_borrow.data.AlertDto;
import com.example.sm_borrow.network.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView requestRecyclerView, availableRecyclerView;
    private NotificationAdapter requestAdapter, availableAdapter;

    private List<AlertDto> requestList, availableList; // 구해요, 있어요 리스트
    private ApiService apiService;
    private Long userId = 1L; // 현재 사용자 ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // 네비게이션 뷰 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.nav_chat) {
                        startActivity(new Intent(NotificationActivity.this, ChatListActivity.class));
                        return true;
                    } else if (itemId == R.id.nav_home) {
                        startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                        return true;
                    } else if (itemId == R.id.nav_mypage) {
                        Intent intentm = new Intent(NotificationActivity.this, MyPageActivity.class);
                        startActivity(intentm);
                        return true;
                    } else {
                        return false;
                    }
        });


        // RecyclerView 초기화
        requestRecyclerView = findViewById(R.id.recycler_view_request);
        availableRecyclerView = findViewById(R.id.recycler_view_available);

        requestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        availableRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 어댑터 초기화
        requestList = new ArrayList<>();
        availableList = new ArrayList<>();

        requestAdapter = new NotificationAdapter(this, requestList, this::updateAlertStatus);
        availableAdapter = new NotificationAdapter(this, availableList, this::updateAlertStatus);

        requestRecyclerView.setAdapter(requestAdapter);
        availableRecyclerView.setAdapter(availableAdapter);

        // Retrofit API 설정
        apiService = RetrofitClient.getApiService();
        fetchAlerts();
    }

    /**
     * 서버에서 알림 데이터를 가져와 구해요와 있어요로 나눕니다.
     */
    private void fetchAlerts() {
        apiService.getAlerts(userId).enqueue(new Callback<List<AlertDto>>() {
            @Override
            public void onResponse(Call<List<AlertDto>> call, Response<List<AlertDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestList.clear();
                    availableList.clear();

                    for (AlertDto alert : response.body()) {
                        if (alert.getBorrowerId().equals(userId)) {
                            requestList.add(alert); // 구해요
                        } else if (alert.getLenderId().equals(userId)) {
                            availableList.add(alert); // 있어요
                        }
                    }

                    requestAdapter.notifyDataSetChanged();
                    availableAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NotificationActivity.this, "알림 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AlertDto>> call, Throwable t) {
                Log.e("NotificationActivity", "Error fetching alerts", t);
                Toast.makeText(NotificationActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 알림 상태를 업데이트합니다 (승인 또는 거절).
     */
    private void updateAlertStatus(AlertDto alert, String status) {
        apiService.updateAlertStatus(alert.getId(), status).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(NotificationActivity.this, status + " 처리 완료", Toast.LENGTH_SHORT).show();
                    fetchAlerts(); // 업데이트 후 새로고침
                } else {
                    Toast.makeText(NotificationActivity.this, "상태 업데이트 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("NotificationActivity", "Error updating alert status", t);
            }
        });
    }
}