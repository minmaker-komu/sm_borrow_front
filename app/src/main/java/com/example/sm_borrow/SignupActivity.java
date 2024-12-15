package com.example.sm_borrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    //뷰 변수
    private EditText emailField, nameField, passwordField, confirmPasswordField;
    private Button signUpButton;

    //데이터 저장용
    private String email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 뷰
        emailField = findViewById(R.id.emailFeild);
        nameField = findViewById(R.id.nameFeild);
        passwordField = findViewById(R.id.passwordFeild);
        confirmPasswordField = findViewById(R.id.userInfoField);
        signUpButton = findViewById(R.id.signupButton);

        // 버튼 클릭 이벤트
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값 가져오기
                email = emailField.getText().toString().trim();
                name = nameField.getText().toString().trim();
                String passwordInput = passwordField.getText().toString().trim();
                String confirmPassword = confirmPasswordField.getText().toString().trim();

                //유효성
                if (email.isEmpty() || name.isEmpty() || passwordInput.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "모든 필드를 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if (!passwordInput.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                } else {
                    //여기부터 서버
                    password = passwordInput;

                    Toast.makeText(SignupActivity.this, "입력 정보 저장 완료!", Toast.LENGTH_SHORT).show();
                    Log.d("SignUpActivity", "Email: " + email);
                    Log.d("SignUpActivity", "Name: " + name);
                    Log.d("SignUpActivity", "Password: " + password);

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
