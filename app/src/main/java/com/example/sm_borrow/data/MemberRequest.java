package com.example.sm_borrow.data;

public class MemberRequest {
    private String username; // 서버에서 username 필드로 매핑
    private String password;

    public MemberRequest(String email, String password) {
        this.username = email; // email 값을 username 필드에 할당
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
