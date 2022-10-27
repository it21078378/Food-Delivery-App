package com.example.fooapp11.model;

public class PaidModel {

    public String name;
    public String img;
    public double total;
    public int quantity;

    public PaidModel() {
    }

    public PaidModel(String name, String img, double total, int quantity) {
        this.name = name;
        this.img = img;
        this.total = total;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

