package com.example.house.javabean;

import java.util.Date;

public class Tenant {
    private String tenantId;
    private String name;
    private String phone;
    private String idCard;
    private String workPlace;
    private String email;
    private Date createdAt;

    // 构造方法
    public Tenant() {}

    public Tenant(String tenantId, String name, String phone,
                  String idCard, String workPlace, String email) {
        this.tenantId = tenantId;
        this.name = name;
        this.phone = phone;
        this.idCard = idCard;
        this.workPlace = workPlace;
        this.email = email;
    }

    // Getter和Setter方法
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }

    public String getWorkPlace() { return workPlace; }
    public void setWorkPlace(String workPlace) { this.workPlace = workPlace; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}