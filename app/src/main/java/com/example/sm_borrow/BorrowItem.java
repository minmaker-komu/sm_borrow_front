package com.example.sm_borrow;

import java.time.LocalDateTime;

public class BorrowItem {
    private String itemName;
    private String price;
    private LocalDateTime createdAt;

    public BorrowItem(String itemName, String price, LocalDateTime createdAt) {
        this.itemName = itemName;
        this.price = price;
        this.createdAt = createdAt;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

