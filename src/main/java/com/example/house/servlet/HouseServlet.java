package com.example.house.servlet;

import com.example.house.dao.HouseDAO;
import com.example.house.javabean.House;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/house")
public class HouseServlet extends HttpServlet {
    private HouseDAO houseDAO = new HouseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listHouses(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteHouse(request, response);
                break;
            case "search":
                searchHouses(request, response);
                break;
            case "vacant":
                showVacantHouses(request, response);
                break;
            default:
                listHouses(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addHouse(request, response);
        } else if ("update".equals(action)) {
            updateHouse(request, response);
        }
    }

    private void listHouses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<House> houses = houseDAO.getAllHouses();
        request.setAttribute("houses", houses);
        request.getRequestDispatcher("/admin/house_manage.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/house_add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String houseId = request.getParameter("id");
        House house = houseDAO.getHouseById(houseId);
        request.setAttribute("house", house);
        request.getRequestDispatcher("/admin/house_edit.jsp").forward(request, response);
    }

    private void addHouse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String houseId = request.getParameter("houseId");
        String address = request.getParameter("address");
        double area = Double.parseDouble(request.getParameter("area"));
        int bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
        double rent = Double.parseDouble(request.getParameter("rent"));
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        House house = new House(houseId, address, area, bedrooms, rent, status, description);

        if (houseDAO.addHouse(house)) {
            request.setAttribute("message", "房屋添加成功！");
        } else {
            request.setAttribute("error", "房屋添加失败！");
        }

        listHouses(request, response);
    }

    private void updateHouse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String houseId = request.getParameter("houseId");
        String address = request.getParameter("address");
        double area = Double.parseDouble(request.getParameter("area"));
        int bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
        double rent = Double.parseDouble(request.getParameter("rent"));
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        House house = new House(houseId, address, area, bedrooms, rent, status, description);

        if (houseDAO.updateHouse(house)) {
            request.setAttribute("message", "房屋信息更新成功！");
        } else {
            request.setAttribute("error", "房屋信息更新失败！");
        }

        listHouses(request, response);
    }

    private void deleteHouse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String houseId = request.getParameter("id");

        if (houseDAO.deleteHouse(houseId)) {
            request.setAttribute("message", "房屋删除成功！");
        } else {
            request.setAttribute("error", "房屋删除失败！");
        }

        listHouses(request, response);
    }

    private void searchHouses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        List<House> houses = houseDAO.searchHousesByAddress(keyword);
        request.setAttribute("houses", houses);
        request.setAttribute("searchKeyword", keyword);
        request.getRequestDispatcher("/admin/house_manage.jsp").forward(request, response);
    }

    private void showVacantHouses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<House> houses = houseDAO.getVacantHouses();
        request.setAttribute("houses", houses);
        request.setAttribute("isVacant", true);
        request.getRequestDispatcher("/user/view_houses.jsp").forward(request, response);
    }
}