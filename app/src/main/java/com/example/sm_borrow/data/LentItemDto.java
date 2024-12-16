package com.example.sm_borrow.data;

public class LentItemDto {
    private Long id;
    private Long itemId;
    private Long memberId;
    private String itemName;
    private int price;
    private String specialNote;
    private String createdAt;

    // 기본 생성자
    public LentItemDto() {}

    // 생성자
    public LentItemDto(Long id, Long itemId, Long memberId, String itemName, int price, String specialNote, String createdAt) {
        this.id = id;
        this.itemId = itemId;
        this.memberId = memberId;
        this.itemName = itemName;
        this.price = price;
        this.specialNote = specialNote;
        this.createdAt = createdAt;
    }

    // Getter 및 Setter
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
