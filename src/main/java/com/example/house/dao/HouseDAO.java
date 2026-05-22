package com.example.house.dao;

import com.example.house.javabean.House;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO {

    // 添加房屋
    public boolean addHouse(House house) {
        String sql = "INSERT INTO houses (house_id, address, area, bedrooms, rent, status, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, house.getHouseId());
            pstmt.setString(2, house.getAddress());
            pstmt.setDouble(3, house.getArea());
            pstmt.setInt(4, house.getBedrooms());
            pstmt.setDouble(5, house.getRent());
            pstmt.setString(6, house.getStatus());
            pstmt.setString(7, house.getDescription());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有房屋
    public List<House> getAllHouses() {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM houses ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                House house = new House();
                house.setHouseId(rs.getString("house_id"));
                house.setAddress(rs.getString("address"));
                house.setArea(rs.getDouble("area"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setRent(rs.getDouble("rent"));
                house.setStatus(rs.getString("status"));
                house.setDescription(rs.getString("description"));
                house.setCreatedAt(rs.getTimestamp("created_at"));

                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houses;
    }

    // 根据ID获取房屋
    public House getHouseById(String houseId) {
        House house = null;
        String sql = "SELECT * FROM houses WHERE house_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, houseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                house = new House();
                house.setHouseId(rs.getString("house_id"));
                house.setAddress(rs.getString("address"));
                house.setArea(rs.getDouble("area"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setRent(rs.getDouble("rent"));
                house.setStatus(rs.getString("status"));
                house.setDescription(rs.getString("description"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return house;
    }

    // 更新房屋信息
    public boolean updateHouse(House house) {
        String sql = "UPDATE houses SET address = ?, area = ?, bedrooms = ?, rent = ?, status = ?, description = ? WHERE house_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, house.getAddress());
            pstmt.setDouble(2, house.getArea());
            pstmt.setInt(3, house.getBedrooms());
            pstmt.setDouble(4, house.getRent());
            pstmt.setString(5, house.getStatus());
            pstmt.setString(6, house.getDescription());
            pstmt.setString(7, house.getHouseId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除房屋
    public boolean deleteHouse(String houseId) {
        String sql = "DELETE FROM houses WHERE house_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, houseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 查询空置房屋
    public List<House> getVacantHouses() {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM houses WHERE status = 'vacant' ORDER BY rent ASC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                House house = new House();
                house.setHouseId(rs.getString("house_id"));
                house.setAddress(rs.getString("address"));
                house.setArea(rs.getDouble("area"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setRent(rs.getDouble("rent"));
                house.setStatus(rs.getString("status"));
                house.setDescription(rs.getString("description"));

                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houses;
    }

    // 根据地址搜索房屋
    public List<House> searchHousesByAddress(String keyword) {
        List<House> houses = new ArrayList<>();
        String sql = "SELECT * FROM houses WHERE address LIKE ? ORDER BY rent ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                House house = new House();
                house.setHouseId(rs.getString("house_id"));
                house.setAddress(rs.getString("address"));
                house.setArea(rs.getDouble("area"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setRent(rs.getDouble("rent"));
                house.setStatus(rs.getString("status"));
                house.setDescription(rs.getString("description"));

                houses.add(house);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houses;
    }
}