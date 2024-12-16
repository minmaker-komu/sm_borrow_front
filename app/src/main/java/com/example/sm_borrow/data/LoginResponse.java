package com.example.sm_borrow.data;

public class LoginResponse {
    private String token; // JWT 토큰
    private String message; // 응답 메시지
    private Long memberId; // 멤버 ID

    // 생성자
    public LoginResponse(String token, String message, Long memberId) {
        this.token = token;
        this.message = message;
        this.memberId = memberId;
    }

    // Getter 및 Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
