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
    // ...
}

