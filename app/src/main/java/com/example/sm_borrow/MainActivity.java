package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                // 현재 페이지
                return true;
            } else if (itemId == R.id.nav_mypage) {
                Intent intentm = new Intent(this, MyPageActivity.class);
                startActivity(intentm);
                return true;
            } else {
                return false;
            }
        });


        // Add Buttons in Main Page
        Button rentButton = findViewById(R.id.rentButton);
        rentButton.setOnClickListener(v -> Toast.makeText(this, "Rent button clicked!", Toast.LENGTH_SHORT).show());

        Button lendButton = findViewById(R.id.lendButton);
        lendButton.setOnClickListener(v -> Toast.makeText(this, "Lend button clicked!", Toast.LENGTH_SHORT).show());
    }
}