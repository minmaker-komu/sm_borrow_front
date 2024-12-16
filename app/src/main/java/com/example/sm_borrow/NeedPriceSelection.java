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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NeedPriceSelection extends AppCompatActivity {

    private ApiService apiService;

    private TextView itemNameTextView;
    private int selectedPrice = 500; // 기본 가격 선택값
    private EditText specialNoteEditText;
    private String buttonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_price_selection);

        // UI 요소 초기화
        itemNameTextView = findViewById(R.id.text_button_info);
        Button submitButton = findViewById(R.id.submit);
        specialNoteEditText = findViewById(R.id.special);

        // 텍스트 뷰에 버튼 정보 표시 (예시)
        TextView textView = findViewById(R.id.text_button_info);
        textView.setText("선택된 물품: " + buttonInfo);

        //가격 선택 버튼
        Button price500 = findViewById(R.id.price500);
        Button price1000 = findViewById(R.id.price1000);
        Button price1500 = findViewById(R.id.price1500);

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
            //서버 전송이 없습니다.
            startActivity(new Intent(NeedPriceSelection.this, MainActivity.class));
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
            } else {
                return false;
            }
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
            //sendDataToServer(itemName, selectedPrice, specialNote, userId);
        });
    });

//    private void sendDataToServer(String itemName, int price, String specialNote, Long userId) {
//        // ItemRequest 객체 생성
//        ItemRequest itemRequest = new ItemRequest(null, itemName, price, specialNote);

        //더미
            long userId = 1L;
            String specialNote = "1s";
            ItemRequest itemRequest = new ItemRequest(null, null, null, null);

        // API 호출
        apiService.addLentItem(itemRequest, userId, specialNote).enqueue(new Callback<LentItem>() {
            @Override
            public void onResponse(Call<LentItem> call, Response<LentItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(NeedPriceSelection.this, "등록 완료!", Toast.LENGTH_SHORT).show();

                    // MainActivity로 이동
                    Intent intent = new Intent(NeedPriceSelection.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(NeedPriceSelection.this, "서버 오류 발생", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LentItem> call, Throwable t) {
                Toast.makeText(NeedPriceSelection.this, "네트워크 오류 발생: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
