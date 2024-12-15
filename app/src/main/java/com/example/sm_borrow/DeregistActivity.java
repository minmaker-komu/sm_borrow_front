package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DeregistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deregist);

        Button button = findViewById(R.id.confirmButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(DeregistActivity.this, DeregistConfirmActivity.class);
            startActivity(intent);
        });
    }
}