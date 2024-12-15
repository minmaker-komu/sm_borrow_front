package com.example.sm_borrow;

public class Item {
    private String name;
    private String price;
    private String status;

    public Item(String name, String price, String status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getStatus() { return status; }
}
