package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //알림
        ImageButton bellButton = findViewById(R.id.bell);
        bellButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);  // ChatActivity 시작
        });

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                Intent intent = new Intent(MainActivity.this, ChatListActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_home) {
                // 현재 페이지
                return true;
            } else if (itemId == R.id.nav_mypage) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });

        // Add Buttons in Main Page
        Button rentButton = findViewById(R.id.rentButton);
        rentButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NeedActivity.class)));

        Button lendButton = findViewById(R.id.lendButton);
        lendButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HaveActivity.class)));
    }
}