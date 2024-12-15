package com.example.sm_borrow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private WebSocket webSocket;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private EditText editMessage;
    private Button buttonSend;

    private final List<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recycler_chat);
        editMessage = findViewById(R.id.edit_message);
        buttonSend = findViewById(R.id.button_send);
        TextView chatTitle = findViewById(R.id.text_chat_title);

        // 아이템 이름 설정
        String itemName = getIntent().getStringExtra("ITEM_NAME");
        chatTitle.setText(itemName + " - 거래 중");

        // RecyclerView 설정
        chatAdapter = new ChatAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // WebSocket 연결
        connectWebSocket();

        // 메시지 전송 버튼
        buttonSend.setOnClickListener(v -> {
            String message = editMessage.getText().toString();
            if (!message.isEmpty()) {
                webSocket.send(message); // 서버로 메시지 전송
                messages.add("나: " + message);
                chatAdapter.notifyItemInserted(messages.size() - 1);
                editMessage.setText("");
            }
        });
    }

    private void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://yourserver.com/ws-chat").build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                runOnUiThread(() -> {
                    messages.add("상대: " + text);
                    chatAdapter.notifyItemInserted(messages.size() - 1);
                });
            }
        });
    }
}
