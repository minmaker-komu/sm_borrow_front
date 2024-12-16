package com.example.sm_borrow.data;

import java.time.LocalDateTime;

public class BorrowedItemDto {
    private Long id; // 빌린 물건 ID
    private Long itemId; // 아이템 ID
    private String itemName; // 아이템 이름
    private int price; // 가격
    private String specialNote; // 특별사항
    private LocalDateTime borrowedTime; // 빌린 시간

    public BorrowedItemDto(Long id, Long itemId, String itemName, int price, String specialNote, LocalDateTime borrowedTime) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.specialNote = specialNote;
        this.borrowedTime = borrowedTime;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public LocalDateTime getBorrowedTime() {
        return borrowedTime;
    }

    public void setBorrowedTime(LocalDateTime borrowedTime) {
        this.borrowedTime = borrowedTime;
    }
}
