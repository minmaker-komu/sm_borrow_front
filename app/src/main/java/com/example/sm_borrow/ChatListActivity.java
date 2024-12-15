package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private List<ChatItem> chatItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        recyclerView = findViewById(R.id.recycler_chat_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 서버에서 채팅 목록 불러오기
        chatItems = fetchChatListFromServer();

        adapter = new ChatListAdapter(this, chatItems);
        recyclerView.setAdapter(adapter);

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                //현재
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(ChatListActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(ChatListActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private List<ChatItem> fetchChatListFromServer() {
        // TODO: Retrofit을 사용해 서버 API에서 채팅 목록을 불러옵니다.
        List<ChatItem> items = new ArrayList<>();
        items.add(new ChatItem("https://example.com/item1.jpg", "C-type 충전기", "그럼 명신관에서 봐요!"));
        items.add(new ChatItem("https://example.com/item2.jpg", "삼성 노트북 충전기", "이따 몇 시쯤 만날까요?"));
        return items;
    }
}
