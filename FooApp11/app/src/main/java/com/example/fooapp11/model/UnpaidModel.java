package com.example.fooapp11.model;

public class UnpaidModel {
    public String name;
    public String img;
    public double total;
    public String quantity;
    public String uid;

    public UnpaidModel() {
    }

    public UnpaidModel(String name, String img, double total, String quantity, String uid) {
        this.name = name;
        this.img = img;
        this.total = total;
        this.quantity = quantity;
        this.uid = uid;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
