package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceSelection extends AppCompatActivity {

    private ApiService apiService;

    private TextView itemNameTextView;
    private String selectedPrice = "500원"; // 기본 가격 선택값
    private EditText specialNoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_selection);

        // UI 요소 초기화
        itemNameTextView = findViewById(R.id.text_button_info);
        Button price500 = findViewById(R.id.button_price_500);
        Button price1000 = findViewById(R.id.button_price_1000);
        Button price1500 = findViewById(R.id.button_price_1500);
        Button submitButton = findViewById(R.id.button_submit);
        specialNoteEditText = findViewById(R.id.edit_special_note);

        // 서버 통신 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("172.20.5.227") // 서버의 BASE URL 설정
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // 선택된 아이템 이름 받기
        String itemName = getIntent().getStringExtra("BUTTON_INFO");
        itemNameTextView.setText("선택된 물품: " + itemName);

        // 가격 버튼 클릭 이벤트
        price500.setOnClickListener(v -> selectedPrice = "500원");
        price1000.setOnClickListener(v -> selectedPrice = "1000원");
        price1500.setOnClickListener(v -> selectedPrice = "1500원");

        // 등록 버튼 클릭 이벤트
        submitButton.setOnClickListener(v -> {
            String specialNote = specialNoteEditText.getText().toString();

            // 서버로 데이터 전송
            sendDataToServer(itemName, selectedPrice, specialNote);
        });



        // Intent로 넘어온 버튼 정보 받기
        String buttonInfo = getIntent().getStringExtra("BUTTON_INFO");

        // 텍스트 뷰에 버튼 정보 표시 (예시)
        TextView textView = findViewById(R.id.text_button_info);
        textView.setText("선택된 물품: " + buttonInfo);
    }

    private void sendDataToServer(String itemName, String price, String specialNote) {
        ItemRequest itemRequest = new ItemRequest(itemName, price, specialNote);

        apiService.saveItem(itemRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(PriceSelection.this, "등록 완료!", Toast.LENGTH_SHORT).show();
                    // MainActivity로 이동
                    Intent intent = new Intent(PriceSelection.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(PriceSelection.this, "서버 오류 발생", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PriceSelection.this, "네트워크 오류 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}