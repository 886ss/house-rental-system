package com.example.house.javabean;

import java.util.Date;

public class Contract {
    private String contractId;
    private String houseId;
    private String tenantId;
    private Date startDate;
    private Date endDate;
    private double deposit;
    private double rent;
    private String status;
    private Date createdAt;

    // 构造方法
    public Contract() {}

    public Contract(String contractId, String houseId, String tenantId,
                    Date startDate, Date endDate, double deposit, double rent) {
        this.contractId = contractId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.rent = rent;
    }

    // Getter和Setter方法
    public String getContractId() { return contractId; }
    public void setContractId(String contractId) { this.contractId = contractId; }

    public String getHouseId() { return houseId; }
    public void setHouseId(String houseId) { this.houseId = houseId; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }

    public double getRent() { return rent; }
    public void setRent(double rent) { this.rent = rent; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}