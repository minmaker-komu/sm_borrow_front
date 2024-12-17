package com.example.sm_borrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NeedResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_result);

        EditText searchKeyword = findViewById(R.id.search_keyword);
        RecyclerView searchResultList = findViewById(R.id.search_result_list);

        // 전달된 검색어 가져오기
        String keyword = getIntent().getStringExtra("SEARCH_KEYWORD");
        Long memberId = getIntent().getLongExtra("MEMBER_ID", -1);
        searchKeyword.setText(keyword);

        // RecyclerView 설정
        searchResultList.setLayoutManager(new LinearLayoutManager(this));
        List<String> results = getSearchResults(keyword);
        // Example usage
        NeedResultAdapter adapter = new NeedResultAdapter(this, results, memberId);
        searchResultList.setAdapter(adapter);

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(NeedResultActivity.this, ChatListActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(NeedResultActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(NeedResultActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private List<String> getSearchResults(String keyword) {
        List<String> results = new ArrayList<>();
        switch (keyword) {
            case "충전기":
                results.add("8핀 충전기");
                results.add("C타입 충전기");
                results.add("5핀 충전기");
                results.add("LG 노트북 충전기");
                results.add("삼성 노트북 충전기");
                results.add("Mac 충전기");
                break;
            case "마우스":
                results.add("로지텍 마우스");
                results.add("무선 마우스");
                results.add("게이밍 마우스");
                break;
            case "공학계산기":
                results.add("카시오 계산기");
                results.add("샤프 계산기");
                results.add("TI 공학용 계산기");
                break;
            case "보조배터리":
                results.add("샤오미 보조배터리");
                results.add("삼성 보조배터리");
                results.add("Anker 보조배터리");
                break;
            case "사물함":
                results.add("철제 사물함");
                results.add("플라스틱 사물함");
                results.add("우드 사물함");
                break;
            case "이어폰":
                results.add("애플 이어폰");
                results.add("삼성 이어폰");
                results.add("소니 이어폰");
                break;
            default:
                results.add("검색 결과가 없습니다.");
                break;
        }
        return results;
    }
}
