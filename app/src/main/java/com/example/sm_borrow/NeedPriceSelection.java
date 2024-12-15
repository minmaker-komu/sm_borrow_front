package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NeedPriceSelection extends AppCompatActivity {

    private String selectedPrice = "500원"; // 기본 가격 선택값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_price_selection);
        Button submitButton = findViewById(R.id.submit);

        // Intent로 넘어온 버튼 정보 받기
        String buttonInfo = getIntent().getStringExtra("BUTTON_INFO");

        // 텍스트 뷰에 버튼 정보 표시 (예시)
        TextView textView = findViewById(R.id.text_button_info);
        textView.setText("선택된 물품: " + buttonInfo);

        //가격 선택 버튼
        Button price500 = findViewById(R.id.price500);
        Button price1000 = findViewById(R.id.price1000);
        Button price1500 = findViewById(R.id.price1500);

        price500.setOnClickListener(v -> {
            selectedPrice = "500원";
            price500.setBackground(getDrawable(R.drawable.selected_button));
            price1000.setBackground(getDrawable(R.drawable.common_button));
            price1500.setBackground(getDrawable(R.drawable.common_button));
        });
        price1000.setOnClickListener(v -> {
            selectedPrice = "1000원";
            price500.setBackground(getDrawable(R.drawable.common_button));
            price1000.setBackground(getDrawable(R.drawable.selected_button));
            price1500.setBackground(getDrawable(R.drawable.common_button));
        });
        price1500.setOnClickListener(v -> {
            selectedPrice = "1500원";
            price500.setBackground(getDrawable(R.drawable.common_button));
            price1000.setBackground(getDrawable(R.drawable.common_button));
            price1500.setBackground(getDrawable(R.drawable.selected_button));
        });

        // 등록 버튼 클릭 이벤트
        submitButton.setOnClickListener(v -> {
            //서버 전송이 없습니다.
            startActivity(new Intent(NeedPriceSelection.this, MainActivity.class));
        });

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(NeedPriceSelection.this, ChatListActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(NeedPriceSelection.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(NeedPriceSelection.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }
}