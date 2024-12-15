package com.example.sm_borrow.data;


import java.time.LocalDateTime;

public class LentItem {
    private Long id;
    private Long itemId;
    private String specialNote;
    private Long userId;
    private String lentTime; // 서버에서 반환된 시간 (ISO 8601)

    public LentItem(Long id, Long itemId, String specialNote, Long userId, String lentTime) {
        this.id = id;
        this.itemId = itemId;
        this.specialNote = specialNote;
        this.userId = userId;
        this.lentTime = lentTime;
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

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLentTime() {
        return lentTime;
    }

    public void setLentTime(String lentTime) {
        this.lentTime = lentTime;
    }
}
