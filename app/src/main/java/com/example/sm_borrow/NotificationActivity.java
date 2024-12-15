package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.OnItemActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        RecyclerView requestRecyclerView = findViewById(R.id.recycler_view_request);
        RecyclerView availableRecyclerView = findViewById(R.id.recycler_view_available);

        List<NotificationItem> requestItems = fetchDataFromServer();
        List<NotificationItem> availableItems = fetchDataFromServer();

        // 어댑터 설정
        requestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestRecyclerView.setAdapter(new NotificationAdapter(this, requestItems, this));

        availableRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        availableRecyclerView.setAdapter(new NotificationAdapter(this, availableItems, this));

        // 네비게이션 뷰 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(NotificationActivity.this, ChatActivity.class));
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
    }

    private List<NotificationItem> fetchDataFromServer() {
        List<NotificationItem> data = new ArrayList<>();
        data.add(new NotificationItem("https://example.com/image1.jpg", "C-type 충전기", "500원/시간 당", ""));
        data.add(new NotificationItem("https://example.com/image2.jpg", "삼성 노트북 충전기", "500원/시간 당", ""));
        return data;
    }

    @Override
    public void onAccept(NotificationItem item, NotificationAdapter.ViewHolder holder) {
        item.setStatusMessage("승인되었습니다.");
        holder.statusMessage.setText(item.getStatusMessage());
        // 서버로 상태 전송 로직 추가
    }

    @Override
    public void onReject(NotificationItem item, NotificationAdapter.ViewHolder holder) {
        item.setStatusMessage("거절되었습니다.");
        holder.statusMessage.setText(item.getStatusMessage());
        // 서버로 상태 전송 로직 추가
    }
}
