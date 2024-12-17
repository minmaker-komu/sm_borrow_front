package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_borrow.data.MemberRequest;
import com.example.sm_borrow.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    // 뷰 변수
    private EditText emailField, passwordField, confirmPasswordField;
    private Button signUpButton;

    // 데이터 저장용
    private String email, password;

    // API 서비스
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 뷰 초기화
        emailField = findViewById(R.id.emailFeild);
        passwordField = findViewById(R.id.passwordFeild);
        confirmPasswordField = findViewById(R.id.userInfoField); // 확인 비밀번호 필드
        signUpButton = findViewById(R.id.signupButton);

        // Retrofit 초기화
        apiService = RetrofitClient.getApiService();

        // 버튼 클릭 이벤트
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 값 가져오기
                email = emailField.getText().toString().trim();
                String passwordInput = passwordField.getText().toString().trim();
                String confirmPassword = confirmPasswordField.getText().toString().trim();

                // 유효성 검사
                if (email.isEmpty() || passwordInput.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "모든 필드를 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if (!passwordInput.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                } else {
                    // 서버로 데이터 전송
                    password = passwordInput;
                    sendSignUpDataToServer(email, password);
                }
            }
        });
    }

    /**
     * 서버로 회원가입 데이터 전송
     */
    private void sendSignUpDataToServer(String email, String password) {
        // MemberRequest 객체 생성
        MemberRequest memberRequest = new MemberRequest(email, password); // email -> username

        apiService.signUp(memberRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                    finish(); // 회원가입 완료 후 액티비티 종료
                } else {
                    Toast.makeText(SignupActivity.this, "회원가입 실패: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("SignupActivity", "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "서버 연결 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("SignupActivity", "Failure: " + t.getMessage(), t);
            }
        });
    }
}
