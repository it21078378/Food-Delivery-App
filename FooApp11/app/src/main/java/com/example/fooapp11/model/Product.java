package com.example.fooapp11.model;

public class Product {
    private String ptitle, imgUrl, pdescription;
    private int pamount;

    public Product() {
    }

    public Product(String ptitle, String imgUrl, String pdescription, int pamount) {
        this.ptitle = ptitle;
        this.imgUrl = imgUrl;
        this.pdescription = pdescription;
        this.pamount = pamount;
    }

    public Product(String name, int price, String description) {
        this.ptitle = name;
        this.pamount = price;
        this.pdescription = description;
    }


    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public int getPamount() {
        return pamount;
    }

    public void setPamount(int pamount) {
        this.pamount = pamount;
    }
}
