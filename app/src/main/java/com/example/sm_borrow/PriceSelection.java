package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sm_borrow.data.LentItem;
import com.example.sm_borrow.ItemRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceSelection extends AppCompatActivity {

    private ApiService apiService;

    private TextView itemNameTextView;
    private int selectedPrice = 500; // 기본 가격 선택값
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

        // Retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.5.227:8080/") // 서버의 BASE URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // Intent로 전달된 아이템 이름 받기
        String itemName = getIntent().getStringExtra("BUTTON_INFO");
        if (itemName != null) {
            itemNameTextView.setText("선택된 물품: " + itemName);
        } else {
            Toast.makeText(this, "아이템 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 가격 버튼 클릭 이벤트
        price500.setOnClickListener(v -> selectedPrice = 500);
        price1000.setOnClickListener(v -> selectedPrice = 1000);
        price1500.setOnClickListener(v -> selectedPrice = 1500);

        // 등록 버튼 클릭 이벤트
        submitButton.setOnClickListener(v -> {
            String specialNote = specialNoteEditText.getText().toString().trim();

            if (specialNote.isEmpty()) {
                Toast.makeText(this, "특이사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 예시 사용자 ID
            Long userId = 1L;

            // 서버로 데이터 전송
            sendDataToServer(itemName, selectedPrice, specialNote, userId);
        });
    }

    private void sendDataToServer(String itemName, int price, String specialNote, Long userId) {
        // ItemRequest 객체 생성
        ItemRequest itemRequest = new ItemRequest(null, itemName, price, specialNote);

        // API 호출
        apiService.addLentItem(itemRequest, userId, specialNote).enqueue(new Callback<LentItem>() {
            @Override
            public void onResponse(Call<LentItem> call, Response<LentItem> response) {
                if (response.isSuccessful() && response.body() != null) {
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
            public void onFailure(Call<LentItem> call, Throwable t) {
                Toast.makeText(PriceSelection.this, "네트워크 오류 발생: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
