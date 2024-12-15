package com.example.sm_borrow;

public class BorrowItem {
    private String itemName;
    private String price;
    private String rentalPeriod;

    public BorrowItem(String itemName, String price, String rentalPeriod) {
        this.itemName = itemName;
        this.price = price;
        this.rentalPeriod = rentalPeriod;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPrice() {
        return price;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }
}

