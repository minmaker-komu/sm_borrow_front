package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_borrow.data.LoginRequest;
import com.example.sm_borrow.data.LoginResponse;
import com.example.sm_borrow.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // 뷰 변수
    private EditText emailField, passwordField;
    private Button loginButton;

    // 데이터 저장용
    private String email, password;

    // API 서비스
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // XML 파일 연결

        // 뷰 초기화
        emailField = findViewById(R.id.nameFeild); // 이메일 입력 필드
        passwordField = findViewById(R.id.passwordFeild); // 비밀번호 입력 필드
        loginButton = findViewById(R.id.loginButton); // 로그인 버튼

        // Retrofit 초기화
        apiService = RetrofitClient.getApiService();

        // 버튼 클릭 이벤트
        loginButton.setOnClickListener(v -> {
            // 값 가져오기
            email = emailField.getText().toString().trim();
            password = passwordField.getText().toString().trim();

            // 유효성 검사
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                // 서버로 로그인 요청
                sendLoginDataToServer(email, password);
            }
        });
    }

    /**
     * 서버로 로그인 데이터 전송
     */
    private void sendLoginDataToServer(String email, String password) {
        // LoginRequest 객체 생성
        LoginRequest loginRequest = new LoginRequest(email, password);

        // API 호출
        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();

                    // 메인 액티비티로 이동
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    // 멤버 ID를 Intent로 전달
                    intent.putExtra("MEMBER_ID", loginResponse.getMemberId());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "서버 연결 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "Failure: " + t.getMessage(), t);
            }
        });
    }
}
