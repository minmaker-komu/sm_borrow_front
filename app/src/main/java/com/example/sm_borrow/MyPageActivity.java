package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page); // 작성한 XML 파일과 연결

        // 버튼 설정
        Button infoButton = findViewById(R.id.InfoButton);
        Button myItemButton = findViewById(R.id.MyItem);
        Button borrowItemButton = findViewById(R.id.BorrowItem);
        Button deregistButton = findViewById(R.id.Deregist);

        // 버튼 클릭 시 다른 액티비티로 이동
        infoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyPageActivity.this, MyInfoActivity.class);
            startActivity(intent);
        });

        myItemButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyPageActivity.this, ItemActivity.class);
            startActivity(intent);
        });

        borrowItemButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyPageActivity.this, BorrowItemActivity.class);
            startActivity(intent);
        });

        deregistButton.setOnClickListener(v -> {
            Intent intent = new Intent(MyPageActivity.this, DeregistActivity.class);
            startActivity(intent);
        });

        // 하단 네비게이션 뷰 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(MyPageActivity.this, ChatActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(MyPageActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                // 현재 페이지이므로 이동하지 않음
                return true;
            } else {
                return false;
            }
        });

    }
}
