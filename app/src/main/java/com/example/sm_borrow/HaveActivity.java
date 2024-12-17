package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HaveActivity extends AppCompatActivity {

    Long memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have); // 첫 번째 화면 XML 파일

        memberId = getIntent().getLongExtra("MEMBER_ID", -1);

        // 버튼 설정
        Button btnCharger = findViewById(R.id.btn_charger);
        Button btnMouse = findViewById(R.id.btn_mouse);
        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnBattery = findViewById(R.id.btn_battery);
        Button btnLocker = findViewById(R.id.btn_locker);
        Button btnEarphones = findViewById(R.id.btn_earphones);

        // 버튼 클릭 이벤트
        btnCharger.setOnClickListener(view -> navigateToResult("충전기"));
        btnMouse.setOnClickListener(view -> navigateToResult("마우스"));
        btnCalculator.setOnClickListener(view -> navigateToResult("공학계산기"));
        btnBattery.setOnClickListener(view -> navigateToResult("보조배터리"));
        btnLocker.setOnClickListener(view -> navigateToResult("사물함"));
        btnEarphones.setOnClickListener(view -> navigateToResult("이어폰"));

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(HaveActivity.this, ChatListActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(HaveActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(HaveActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private void navigateToResult(String keyword) {
        Intent intent = new Intent(HaveActivity.this, HaveResultActivity.class);
        intent.putExtra("SEARCH_KEYWORD", keyword); // 검색어 전달
        intent.putExtra("MEMBER_ID", memberId); // MEMBER_ID 전달
        startActivity(intent);
    }
}
