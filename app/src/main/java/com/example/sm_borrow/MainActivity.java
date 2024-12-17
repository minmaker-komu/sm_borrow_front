package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent에서 멤버 ID 가져오기
        Long memberId = getIntent().getLongExtra("MEMBER_ID", -1);

        if (memberId != -1) {
            Log.d("MainActivity", "Logged in as Member ID: " + memberId);
        } else {
            Log.e("MainActivity", "Member ID not received!");
        }

        //알림
        ImageButton bellButton = findViewById(R.id.bell);
        bellButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            intent.putExtra("MEMBER_ID", memberId);
            startActivity(intent);  // ChatActivity 시작
        });

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            Intent intent;

            if (itemId == R.id.nav_chat) {
                // ChatActivity로 이동
                intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("MEMBER_ID", memberId); // MEMBER_ID 전달
                startActivity(intent);
                return true;

            } else if (itemId == R.id.nav_home) {
                // 현재 페이지 (이동하지 않음)
                return true;

            } else if (itemId == R.id.nav_mypage) {
                // MyPageActivity로 이동
                intent = new Intent(MainActivity.this, MyPageActivity.class);
                intent.putExtra("MEMBER_ID", memberId); // MEMBER_ID 전달
                startActivity(intent);
                return true;

            } else {
                return false;
            }
        });


        // Add Buttons in Main Page
        Button rentButton = findViewById(R.id.rentButton);
        rentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HaveActivity로 이동

                Intent intent = new Intent(MainActivity.this, NeedActivity.class);
                intent.putExtra("MEMBER_ID", memberId);
                startActivity(intent);
            }
        });
        //rentButton.setOnClickListener(v -> Toast.makeText(this, "Rent button clicked!", Toast.LENGTH_SHORT).show());

        Button lendButton = findViewById(R.id.lendButton);
        lendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HaveActivity로 이동
                Intent intent = new Intent(MainActivity.this, HaveActivity.class);
                intent.putExtra("MEMBER_ID", memberId);
                startActivity(intent);
            }
        });
        //lendButton.setOnClickListener(v -> Toast.makeText(this, "Lend button clicked!", Toast.LENGTH_SHORT).show());
    }
}