package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NeedPriceSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_price_selection);

        // Intent로 넘어온 버튼 정보 받기
        String buttonInfo = getIntent().getStringExtra("BUTTON_INFO");

        // 텍스트 뷰에 버튼 정보 표시 (예시)
        TextView textView = findViewById(R.id.text_button_info);
        textView.setText("선택된 물품: " + buttonInfo);
    }
}