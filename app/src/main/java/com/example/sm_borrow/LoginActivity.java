package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //뷰 변수
    private EditText emailField, passwordField;
    private Button loginButton;

    //데이터 저장용
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // XML 파일 연결

        // 뷰
        emailField = findViewById(R.id.nameFeild); // 이메일 입력 필드
        passwordField = findViewById(R.id.passwordFeild); // 비밀번호 입력 필드
        loginButton = findViewById(R.id.loginButton); // 로그인 버튼

        // 버튼 클릭 이벤트
        loginButton.setOnClickListener(v -> {
            //값 가져오기
            email = emailField.getText().toString().trim();
            password = passwordField.getText().toString().trim();

            // 유효성 - 추가 필요
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "로그인 성공: " + email, Toast.LENGTH_SHORT).show();

                Log.d("LoginActivity", "Email: " + email);
                Log.d("LoginActivity", "Password: " + password);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
