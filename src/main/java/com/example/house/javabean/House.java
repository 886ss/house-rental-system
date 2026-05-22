package com.example.house.javabean;

import java.util.Date;

public class House {
    private String houseId;
    private String address;
    private double area;
    private int bedrooms;
    private double rent;
    private String status;
    private String description;
    private int ownerId;
    private Date createdAt;

    // 构造方法
    public House() {}

    public House(String houseId, String address, double area, int bedrooms,
                 double rent, String status, String description) {
        this.houseId = houseId;
        this.address = address;
        this.area = area;
        this.bedrooms = bedrooms;
        this.rent = rent;
        this.status = status;
        this.description = description;
    }

    // Getter和Setter方法
    public String getHouseId() { return houseId; }
    public void setHouseId(String houseId) { this.houseId = houseId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }

    public double getRent() { return rent; }
    public void setRent(double rent) { this.rent = rent; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}