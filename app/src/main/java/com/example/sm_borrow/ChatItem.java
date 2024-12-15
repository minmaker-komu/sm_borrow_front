package com.example.sm_borrow;


public class ChatItem {
    private final String imageUrl;
    private final String itemName;
    private final String lastMessage;

    public ChatItem(String imageUrl, String itemName, String lastMessage) {
        this.imageUrl = imageUrl;
        this.itemName = itemName;
        this.lastMessage = lastMessage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
