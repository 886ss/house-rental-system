package com.example.house.dao;

import com.example.house.javabean.Contract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {

    // 创建合同
    public boolean createContract(Contract contract) {
        String sql = "INSERT INTO contracts (contract_id, house_id, tenant_id, start_date, end_date, deposit, rent, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contract.getContractId());
            pstmt.setString(2, contract.getHouseId());
            pstmt.setString(3, contract.getTenantId());
            pstmt.setDate(4, new java.sql.Date(contract.getStartDate().getTime()));
            pstmt.setDate(5, new java.sql.Date(contract.getEndDate().getTime()));
            pstmt.setDouble(6, contract.getDeposit());
            pstmt.setDouble(7, contract.getRent());
            pstmt.setString(8, contract.getStatus());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有合同
    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contracts ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContractId(rs.getString("contract_id"));
                contract.setHouseId(rs.getString("house_id"));
                contract.setTenantId(rs.getString("tenant_id"));
                contract.setStartDate(rs.getDate("start_date"));
                contract.setEndDate(rs.getDate("end_date"));
                contract.setDeposit(rs.getDouble("deposit"));
                contract.setRent(rs.getDouble("rent"));
                contract.setStatus(rs.getString("status"));
                contract.setCreatedAt(rs.getTimestamp("created_at"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    // 终止合同
    public boolean terminateContract(String contractId) {
        String sql = "UPDATE contracts SET status = 'terminated' WHERE contract_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contractId);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                // 更新房屋状态为空置
                String houseId = getHouseIdByContract(contractId);
                if (houseId != null) {
                    updateHouseStatus(houseId, "vacant");
                }
            }

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取合同对应的房屋ID
    private String getHouseIdByContract(String contractId) {
        String sql = "SELECT house_id FROM contracts WHERE contract_id = ?";
        String houseId = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contractId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                houseId = rs.getString("house_id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houseId;
    }

    // 更新房屋状态
    private boolean updateHouseStatus(String houseId, String status) {
        String sql = "UPDATE houses SET status = ? WHERE house_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setString(2, houseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}