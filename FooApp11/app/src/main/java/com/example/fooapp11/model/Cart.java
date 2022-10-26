package com.example.fooapp11.model;

public class Cart {
    private String userId, ptitle, imgUrl, pdescription;
    private int pamount, pquantity, ptotal;


    public Cart() {
    }

    public Cart(String userId, String ptitle, int pamount, int pquantity, int ptotal, String imgUrl, String pdescription) {
        this.userId = userId;
        this.ptitle = ptitle;
        this.imgUrl = imgUrl;
        this.pamount = pamount;
        this.pquantity = pquantity;
        this.ptotal = ptotal;
        this.pdescription = pdescription;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getPamount() {
        return pamount;
    }

    public void setPamount(int pamount) {
        this.pamount = pamount;
    }

    public int getPquantity() {
        return pquantity;
    }

    public void setPquantity(int pquantity) {
        this.pquantity = pquantity;
    }

    public int getPtotal() {
        return ptotal;
    }

    public void setPtotal(int ptotal) {
        this.ptotal = ptotal;
    }
}
