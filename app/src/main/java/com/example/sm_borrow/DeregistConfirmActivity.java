package com.example.sm_borrow;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DeregistConfirmActivity extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deregist_confirm); // XML 파일과 연결

        // 확인 버튼 초기화
        confirmButton = findViewById(R.id.confirmButton);

        // 버튼 클릭 이벤트: 앱 종료
        confirmButton.setOnClickListener(v -> {
            finishAffinity(); // 현재 앱의 모든 액티비티 종료
            System.exit(0);   // 프로세스 종료
        });
    }
}
