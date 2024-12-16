package com.example.sm_borrow;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_borrow.data.AlertDto;
import com.example.sm_borrow.network.ApiService;
import com.example.sm_borrow.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView notificationRecyclerView;
    private NotificationAdapter notificationAdapter;
    private List<AlertDto> alertList;
    private ApiService apiService;
    private Long userId = 1L; // 사용자 ID 예시

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // RecyclerView 초기화
        notificationRecyclerView = findViewById(R.id.recycler_view_request);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        alertList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(this, alertList, (item, holder) -> {
            // 예시 승인 버튼 로직
            Toast.makeText(NotificationActivity.this, "승인 처리: " + item.getId(), Toast.LENGTH_SHORT).show();
        }, (item, holder) -> {
            // 예시 거절 버튼 로직
            Toast.makeText(NotificationActivity.this, "거절 처리: " + item.getId(), Toast.LENGTH_SHORT).show();
        });
        notificationRecyclerView.setAdapter(notificationAdapter);

        // Retrofit 초기화
        apiService = RetrofitClient.getApiService();

        // 알람 데이터 가져오기
        fetchAlerts();
    }

    private void fetchAlerts() {
        apiService.getAlerts(userId).enqueue(new Callback<List<AlertDto>>() {
            @Override
            public void onResponse(Call<List<AlertDto>> call, Response<List<AlertDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    alertList.clear();
                    alertList.addAll(response.body());
                    notificationAdapter.notifyDataSetChanged(); // RecyclerView 업데이트
                } else {
                    Toast.makeText(NotificationActivity.this, "알람 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AlertDto>> call, Throwable t) {
                Toast.makeText(NotificationActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("NotificationActivity", "Error fetching alerts", t);
            }
        });
    }
}
