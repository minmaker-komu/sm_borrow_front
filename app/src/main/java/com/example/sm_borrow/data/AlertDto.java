package com.example.sm_borrow.data;


public class AlertDto {
    private Long id;
    private Long itemId;
    private Long userLenderId;
    private Long userBorrowerId;
    private String status;
    private String createdAt;

    public AlertDto() {}

    public AlertDto(Long id, Long itemId, Long userLenderId, Long userBorrowerId, String status, String createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.userLenderId = userLenderId;
        this.userBorrowerId = userBorrowerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserLenderId() {
        return userLenderId;
    }

    public void setUserLenderId(Long userLenderId) {
        this.userLenderId = userLenderId;
    }

    public Long getUserBorrowerId() {
        return userBorrowerId;
    }

    public void setUserBorrowerId(Long userBorrowerId) {
        this.userBorrowerId = userBorrowerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

