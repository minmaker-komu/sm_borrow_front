package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyInfoActivity extends AppCompatActivity {

    // 뷰 변수 선언
    private EditText emailField, nameField, passwordField, confirmPasswordField;
    private Button correctButton;

    //데이터 저장용
    private String email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info); // XML 파일 연결

        // View 연결
        emailField = findViewById(R.id.emailFeild);
        nameField = findViewById(R.id.nameFeild);
        passwordField = findViewById(R.id.passwordFeild);
        confirmPasswordField = findViewById(R.id.userInfoField);
        correctButton = findViewById(R.id.correct);

        // 수정 버튼
        correctButton.setOnClickListener(v -> {
            email = emailField.getText().toString().trim();
            name = nameField.getText().toString().trim();
            String passwordInput = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();

            // 유효성 검사
            if (email.isEmpty() || name.isEmpty() || passwordInput.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(MyInfoActivity.this, "모든 필드를 입력해주세요!", Toast.LENGTH_SHORT).show();
            } else if (!passwordInput.equals(confirmPassword)) {
                Toast.makeText(MyInfoActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
            } else {

                password = passwordInput;

                // 여기부터 서버
                Toast.makeText(MyInfoActivity.this, "정보가 성공적으로 저장되었습니다!", Toast.LENGTH_SHORT).show();

                Log.d("EditProfileActivity", "Email: " + email);
                Log.d("EditProfileActivity", "Name: " + name);
                Log.d("EditProfileActivity", "Password: " + password);
            }
        });

        //네비
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_mypage);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_chat) {
                startActivity(new Intent(MyInfoActivity.this, ChatListActivity.class));
                return true;
            } else if (itemId == R.id.nav_home) {
                startActivity(new Intent(MyInfoActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_mypage) {
                startActivity(new Intent(MyInfoActivity.this, MyPageActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }
}
