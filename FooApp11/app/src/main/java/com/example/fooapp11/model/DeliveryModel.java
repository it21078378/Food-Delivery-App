package com.example.fooapp11.model;

public class DeliveryModel {

    String id, HNo, building, street, area, landmark;

    public DeliveryModel() {
    }

    public DeliveryModel(String id, String HNo, String building, String street, String area, String landmark) {
        this.id = id;
        this.HNo = HNo;
        this.building = building;
        this.street = street;
        this.area = area;
        this.landmark = landmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHNo() {
        return HNo;
    }

    public void setHNo(String HNo) {
        this.HNo = HNo;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}

