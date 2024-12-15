package com.example.sm_borrow;

public class NotificationItem {
    private String imageUrl;
    private String itemName;
    private String itemPrice;
    private String statusMessage;

    public NotificationItem(String imageUrl, String itemName, String itemPrice, String statusMessage) {
        this.imageUrl = imageUrl;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.statusMessage = statusMessage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
