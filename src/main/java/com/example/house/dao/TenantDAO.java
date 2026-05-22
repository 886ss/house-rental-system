package com.example.house.dao;

import com.example.house.javabean.Tenant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenantDAO {

    // 添加租客
    public boolean addTenant(Tenant tenant) {
        String sql = "INSERT INTO tenants (tenant_id, name, phone, id_card, work_place, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tenant.getTenantId());
            pstmt.setString(2, tenant.getName());
            pstmt.setString(3, tenant.getPhone());
            pstmt.setString(4, tenant.getIdCard());
            pstmt.setString(5, tenant.getWorkPlace());
            pstmt.setString(6, tenant.getEmail());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有租客
    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        String sql = "SELECT * FROM tenants ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tenant tenant = new Tenant();
                tenant.setTenantId(rs.getString("tenant_id"));
                tenant.setName(rs.getString("name"));
                tenant.setPhone(rs.getString("phone"));
                tenant.setIdCard(rs.getString("id_card"));
                tenant.setWorkPlace(rs.getString("work_place"));
                tenant.setEmail(rs.getString("email"));
                tenant.setCreatedAt(rs.getTimestamp("created_at"));

                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenants;
    }

    // 根据ID获取租客
    public Tenant getTenantById(String tenantId) {
        Tenant tenant = null;
        String sql = "SELECT * FROM tenants WHERE tenant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tenantId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tenant = new Tenant();
                tenant.setTenantId(rs.getString("tenant_id"));
                tenant.setName(rs.getString("name"));
                tenant.setPhone(rs.getString("phone"));
                tenant.setIdCard(rs.getString("id_card"));
                tenant.setWorkPlace(rs.getString("work_place"));
                tenant.setEmail(rs.getString("email"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenant;
    }

    // 更新租客信息
    public boolean updateTenant(Tenant tenant) {
        String sql = "UPDATE tenants SET name = ?, phone = ?, id_card = ?, work_place = ?, email = ? WHERE tenant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tenant.getName());
            pstmt.setString(2, tenant.getPhone());
            pstmt.setString(3, tenant.getIdCard());
            pstmt.setString(4, tenant.getWorkPlace());
            pstmt.setString(5, tenant.getEmail());
            pstmt.setString(6, tenant.getTenantId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除租客
    public boolean deleteTenant(String tenantId) {
        String sql = "DELETE FROM tenants WHERE tenant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tenantId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据姓名搜索租客
    public List<Tenant> searchTenantsByName(String name) {
        List<Tenant> tenants = new ArrayList<>();
        String sql = "SELECT * FROM tenants WHERE name LIKE ? ORDER BY name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Tenant tenant = new Tenant();
                tenant.setTenantId(rs.getString("tenant_id"));
                tenant.setName(rs.getString("name"));
                tenant.setPhone(rs.getString("phone"));
                tenant.setIdCard(rs.getString("id_card"));
                tenant.setWorkPlace(rs.getString("work_place"));
                tenant.setEmail(rs.getString("email"));

                tenants.add(tenant);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenants;
    }
}