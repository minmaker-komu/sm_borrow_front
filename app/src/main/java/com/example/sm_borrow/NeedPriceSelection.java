package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sm_borrow.data.LentItemDto;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NeedPriceSelection extends AppCompatActivity {

    private ApiService apiService;

    private TextView itemNameTextView;
    private int selectedPrice = 500; // 기본 가격 선택값
    private EditText specialNoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_price_selection);

        // UI 요소 초기화
        itemNameTextView = findViewById(R.id.text_button_info);
        Button price500 = findViewById(R.id.price500);
        Button price1000 = findViewById(R.id.price1000);
        Button price1500 = findViewById(R.id.price1500);
        Button submitButton = findViewById(R.id.submit);
        specialNoteEditText = findViewById(R.id.special);

        // Retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.5.39:8080/") // 서버의 BASE URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // Intent로 전달된 아이템 이름 받기
        String itemName = getIntent().getStringExtra("BUTTON_INFO");
        //Long memberId = getIntent().getLongExtra("MEMBER_ID", -1);
        if (itemName != null) {
            itemNameTextView.setText("선택된 물품: " + itemName);
        } else {
            Toast.makeText(this, "아이템 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //가격 선택 버튼
        price500.setOnClickListener(v -> {
            selectedPrice = 500;
            price500.setBackground(getDrawable(R.drawable.selected_button));
            price1000.setBackground(getDrawable(R.drawable.common_button));
            price1500.setBackground(getDrawable(R.drawable.common_button));
        });
        price1000.setOnClickListener(v -> {
            selectedPrice = 1000;
            price500.setBackground(getDrawable(R.drawable.common_button));
            price1000.setBackground(getDrawable(R.drawable.selected_button));
            price1500.setBackground(getDrawable(R.drawable.common_button));
        });
        price1500.setOnClickListener(v -> {
            selectedPrice = 1500;
            price500.setBackground(getDrawable(R.drawable.common_button));
            price1000.setBackground(getDrawable(R.drawable.common_button));
            price1500.setBackground(getDrawable(R.drawable.selected_button));
        });

        // 등록 버튼 클릭 이벤트
        submitButton.setOnClickListener(v -> {
            String specialNote = specialNoteEditText.getText().toString().trim();

            if (specialNote.isEmpty()) {
                Toast.makeText(this, "특이사항을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 예시 사용자 ID
            Long userId = 1L; // 실제 사용자 ID를 앱에서 관리해야 함

            // 서버로 데이터 전송
            sendDataToServer(itemName, selectedPrice, specialNote, userId);
            Toast.makeText(this, itemName + selectedPrice + selectedPrice + userId, Toast.LENGTH_SHORT).show();
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
            }
            return true;
        });
    }

    private void sendDataToServer(String itemName, int price, String specialNote, Long userId) {
        // LentItemDto 객체 생성
        LentItemDto lentItemDto = new LentItemDto(
                null, // 서버에서 생성될 ID
                null, // 서버에서 생성될 itemId
                userId, // 사용자 ID
                itemName,
                price,
                specialNote,
                null // 서버에서 생성될 createdAt
        );

        // 디버깅용 JSON 출력
        String jsonRequest = new Gson().toJson(lentItemDto);
        Log.d("DEBUG", "JSON Request: " + jsonRequest);

        // API 호출
        apiService.addLentItem(lentItemDto).enqueue(new Callback<LentItemDto>() {
            @Override
            public void onResponse(Call<LentItemDto> call, Response<LentItemDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(NeedPriceSelection.this, "등록 완료!", Toast.LENGTH_SHORT).show();

                    // MainActivity로 이동
                    Intent intent = new Intent(NeedPriceSelection.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(NeedPriceSelection.this, "서버 오류 발생: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LentItemDto> call, Throwable t) {
                Toast.makeText(NeedPriceSelection.this, "네트워크 오류 발생: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
