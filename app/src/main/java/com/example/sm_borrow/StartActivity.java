package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //로그인
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        //회원가입
        Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}