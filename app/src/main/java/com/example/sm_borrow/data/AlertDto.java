package com.example.sm_borrow.data;

public class AlertDto {
    private Long id;
    private Long itemId;
    private Long lenderId;
    private Long borrowerId;
    private String status;
    private String createdAt;

    // 기본 생성자
    public AlertDto() {}

    public AlertDto(Long id, Long itemId, Long lenderId, Long borrowerId, String status, String createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.lenderId = lenderId;
        this.borrowerId = borrowerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Long getLenderId() { return lenderId; }
    public void setLenderId(Long lenderId) { this.lenderId = lenderId; }

    public Long getBorrowerId() { return borrowerId; }
    public void setBorrowerId(Long borrowerId) { this.borrowerId = borrowerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
